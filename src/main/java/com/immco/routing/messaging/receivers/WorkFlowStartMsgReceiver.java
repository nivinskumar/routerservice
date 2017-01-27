package com.immco.routing.messaging.receivers;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.immco.cache.WorkflowCacheBuilder;
import com.immco.common.DBDC;
import com.immco.common.RouterConstants;
import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.templates.WorkFlowTemplate;
import com.immco.db.service.exec.WorkFlowExecutorService;
import com.immco.routing.RoutingKeyConstants;
import com.immco.routing.messaging.common.NewSurveyMsg;
import com.immco.routing.messaging.common.RoutingCtx;
import com.immco.routing.messaging.common.RoutingMsg;
import com.immco.routing.messaging.senders.MessagingProxy;
import com.immco.routing.utils.LogCtx;
import com.immco.routing.utils.LoggerService;

@Service
public class WorkFlowStartMsgReceiver {

	@Autowired
	private WorkFlowExecutorService wflowExecService;

	@Autowired
	private MessagingProxy messagingProxy;
	
	
	@RabbitListener(queues = RoutingKeyConstants.Survey.SURVEY_INTAKE)
	public void receiveMessage(NewSurveyMsg newSurveyMsg, Message<?> msg) {

		try {
			
			
			if (newSurveyMsg.getRoutingKey().equalsIgnoreCase(RoutingKeyConstants.Survey.SURVEY_INTAKE)&& !newSurveyMsg.isPingMsg()) {
				WorkFlowTemplate workFlowTemplate = WorkflowCacheBuilder.getInstance().checkWorflowExistForConsType(newSurveyMsg.getConstructionType().longValue());
				if(workFlowTemplate!=null){
				wflowExecService.createWorkFlowExec(newSurveyMsg.getConstructionType(), newSurveyMsg.getCwsId(), RouterConstants.Phase.SERVICEABILITY);
				// get the role, group and org level
				TaskExec firstTask = WorkflowCacheBuilder.getInstance().getFirstTask(new BigDecimal(newSurveyMsg.getCwsId()));
				
//				Map taskExcution = new HashMap<>();
//				taskExcution.put("WORK_GROUP_PKEY", firstTask.getWorkGroupPkey());
//				taskExcution.put("ORG_LEVEL", firstTask.getOrgLevel());
//				taskExcution.put("ROLE_PKEY", firstTask.getRolePkey());
//				taskExcution.put("TASK_STATUS", 1);
//				taskExcution.put("CWS_ID", newSurveyMsg.getCwsId());
				DBDC dbdc = wflowExecService.createTaskExecutionEntry(firstTask);
				
				LoggerService.getInstance().info(LogCtx.ROUTING_MSG, newSurveyMsg.getRxLogMsg(), WorkFlowStartMsgReceiver.class);
				
				HashMap<String, String> param = new HashMap<>();
				param.put(RoutingCtx.PARAM_CWS_ID, firstTask.getCwsId().toString());
				param.put(RoutingCtx.PARAM_TASK_ID, firstTask.getTaskId());
				RoutingMsg r = new RoutingMsg(RoutingKeyConstants.Cache.APPLICATION_CACHE, RoutingCtx.TASK_ASSIGN_NEW, param);
				messagingProxy.sendRoutingMsg(r);
//				Thread.sleep(2000);
				
			} else {
				LoggerService.getInstance().info(LogCtx.ROUTING_PING, newSurveyMsg.getRxLogMsg(), WorkFlowStartMsgReceiver.class);
			}
			}
			
		} catch (Exception e) {
			LoggerService.getInstance().error(LogCtx.ROUTING_MSG, "Error in Receiving msg from Message Server.  Attrs=" + newSurveyMsg.toString(), WorkFlowStartMsgReceiver.class, e);
		}
	}

	// @RabbitListener(queues = "queue.bar")
	// public void receiveMessage(RoutingMsg routingMsg) {
	// System.out.println(routingMsg.toString());
	// }

}