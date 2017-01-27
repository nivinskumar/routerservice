package com.immco.db.service.exec;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immco.cache.WorkflowCacheBuilder;
import com.immco.common.DBDC;
import com.immco.common.RouterConstants;
import com.immco.db.dao.exec.WorkFlowExecutorDao;
import com.immco.db.model.job.Org;
import com.immco.db.model.router.exec.SubTaskExec;
import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.exec.UDefTaskExec;
import com.immco.db.model.router.exec.WorkFlowExec;
import com.immco.db.model.router.exec.WorkflowPhaseExec;
import com.immco.db.model.router.templates.SubTaskTemplate;
import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.UDefFormTemplate;
import com.immco.db.model.router.templates.WorkFlowTemplate;
import com.immco.db.model.router.templates.WorkflowPhase;
import com.immco.db.proxy.ConstructionServiceProxy;

@Service("workflowExecutorService")
@Transactional
public class WorkFlowExecutorServiceImpl implements WorkFlowExecutorService {

	@Autowired
	private WorkFlowExecutorDao wfExecDao;

	@Autowired
	private ConstructionServiceProxy constructionProxy;

	private WorkFlowTemplateToExecConvertor convertor = new WorkFlowTemplateToExecConvertor();

	@Override
	public DBDC createWorkFlowExec(BigDecimal constructionTypePkey, Integer cwsId, Integer phase) {

		DBDC dbDc = new DBDC();
		try {
			Org org = getOrg(cwsId, phase);
			if(org == null){
				dbDc.setErrorMsg("Failed to Create WorkflowExec.");
			}
			WorkFlowTemplate workflowTemplate = WorkflowCacheBuilder.getInstance().createWorkFlowTree(constructionTypePkey.longValue());

			WorkFlowExec wfExec = convertor.getWorkFlowExec(workflowTemplate, cwsId);

			wfExecDao.createOrUpdateWorkFlow(wfExec);

			WorkflowPhase[] workflowPhase = workflowTemplate.getMilestoneTemplates();
			for (WorkflowPhase workflowPhaseTemplate : workflowPhase) {
				WorkflowPhaseExec workflowPhaseExec = convertor.getWorkflowPaseExec(wfExec, workflowPhaseTemplate);
				workflowPhaseExec.setWorkflowExecPkey(wfExec.getpKey());
				wfExecDao.createOrUpdateMilestone(workflowPhaseExec);

				TaskTemplate[] taskTemplates = workflowPhaseTemplate.getTaskTemplates();
				for (TaskTemplate taskTemplate : taskTemplates) {
					TaskExec taskExec = convertor.getTaskExec(workflowPhaseExec, taskTemplate, org);
					taskExec.setWorkflowPhaseExecPkey(workflowPhaseExec.getpKey());
					wfExecDao.createOrUpdateTask(taskExec);

					SubTaskTemplate[] subTaskTemplates = taskTemplate.getSubTaskTemplate();
					for (SubTaskTemplate sTaskTemplate : subTaskTemplates) {
						SubTaskExec subTaskExec = convertor.getSubTaskExec(taskExec, sTaskTemplate, org);
						subTaskExec.setTaskExecPkey(taskExec.getpKey());
						wfExecDao.createOrUpdateSubTask(subTaskExec);
					}
					UDefFormTemplate[] udefFormTemplates = taskTemplate.getUdefFormTemplate();
					for (UDefFormTemplate udefFormTemplate : udefFormTemplates) {
						UDefTaskExec udefTaskExec = convertor.getUDefTaskExec(taskExec, udefFormTemplate);
						udefTaskExec.setTaskExecPkey(taskExec.getpKey());
						wfExecDao.createOrUpdateUdefTaskExec(udefTaskExec);
					}
					
					
					
					
				}
			}

			dbDc.setSuccessMsg("WorkflowExec Created...!!!");
		} catch (Exception e) {
			e.printStackTrace();
			dbDc.setErrorMsg(e.getMessage());
		}
		return dbDc;
	}

	private Org getOrg(Integer cwsId, Integer phase) throws Exception {

		List<?> orgData = null;
		if (RouterConstants.Phase.SERVICEABILITY.equals(phase)) {
			orgData = constructionProxy.executePlainSelect("ROUTER_SERVICE", "select rgn_nm, ma_nm, glid_no, psid_no from survey.survey a where cws_id=" + cwsId);
		} else if (RouterConstants.Phase.CONSTRUCTION.equals(phase)) {
			orgData = constructionProxy.executePlainSelect("ROUTER_SERVICE", "select rgn_nm, ma_nm, glid_no, psid_no from job a where cws_id=" + cwsId);
		}

		Org org = null;
		if (orgData != null&& orgData.size()>0) {
			org = new Org();
			List<?> row = (List<?>) orgData.get(0);
			org.setRgnName((String) row.get(0));
			org.setMaName((String) row.get(1));
			org.setGlidNo((Integer) row.get(2));
			org.setPsidNo((Integer) row.get(3));
		}
		return org;
	}

	@Override
	public DBDC createTaskExecutionEntry(TaskExec firstTask) throws Exception {
		wfExecDao.createTaskExecutionEntry(firstTask);
		return null;
	}

}
