package com.immco.db.service.exec;

import java.util.Date;

import com.immco.cache.WorkflowCacheBuilder;
import com.immco.common.RouterConstants;
import com.immco.db.model.job.Org;
import com.immco.db.model.router.exec.WorkflowPhaseExec;
import com.immco.db.model.router.exec.SubTaskExec;
import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.exec.UDefCustomFormExec;
import com.immco.db.model.router.exec.UDefTaskExec;
import com.immco.db.model.router.exec.WorkFlowExec;
import com.immco.db.model.router.templates.WorkflowPhase;
import com.immco.db.model.router.templates.SubTaskTemplate;
import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.UDefFormTemplate;
import com.immco.db.model.router.templates.WorkFlowTemplate;

/**
 * Helper class for WorkFlowExec
 * 
 * @author shajeelawrence
 *
 */
public class WorkFlowTemplateToExecConvertor {

	public WorkFlowExec getWorkFlowExec(WorkFlowTemplate workflowTemplate, Integer cwsId) {
		WorkFlowExec workFlowExec = new WorkFlowExec();
		String constructionType = WorkflowCacheBuilder.getInstance().getConstructionType(workflowTemplate.getConstructionTypePkey());
		workFlowExec.setConstructionType(constructionType);
		workFlowExec.setCrtDt(new Date());
		workFlowExec.setCwsId(cwsId);
		workFlowExec.setExecOutcome(RouterConstants.WorkFlowExecOutcome.IN_PROGRESS);
		workFlowExec.setWorkFlowDesc(workflowTemplate.getWorkflowDesc());
		workFlowExec.setWorkFlowId(workflowTemplate.getWorkflowId());
		workFlowExec.setWorkFlowName(workflowTemplate.getWorkflowName());
		workFlowExec.setWorkOrderId(workflowTemplate.getWorkflowId() + "-" + cwsId);
		workFlowExec.setWorkFlowTemplatePkey(workflowTemplate.getpKey());
		return workFlowExec;
	}

	public WorkflowPhaseExec getWorkflowPaseExec(WorkFlowExec workflowExec, WorkflowPhase workflowPhase) {
		WorkflowPhaseExec milestoneExec = new WorkflowPhaseExec();
		milestoneExec.setCwsId(workflowExec.getCwsId());
		milestoneExec.setWorkflowPhaseDesc(workflowPhase.getWorkflowPhaseDesc());
		milestoneExec.setWorkflowPhaseId(workflowPhase.getWorkflowPhaseId());
		milestoneExec.setWorkflowPhaseName(workflowPhase.getWorkflowPhaseName());
		milestoneExec.setWorkflowPhaseStatus(RouterConstants.MilestoneStatus.NOT_STARTED);
		milestoneExec.setWorkflowExecPkey(workflowExec.getpKey());
		milestoneExec.setWorkorderId(workflowExec.getWorkOrderId());
		return milestoneExec;
	}

	public TaskExec getTaskExec(WorkflowPhaseExec workflowPhaseExec, TaskTemplate taskTemplate, Org org) {
		TaskExec taskExec = new TaskExec();
		taskExec.setCwsId(workflowPhaseExec.getCwsId());
		taskExec.setEndPoint(taskTemplate.getEndPoint());
		taskExec.setGlid(org.getGlidNo());
		taskExec.setIgnoreSla(taskTemplate.isIgnoreSla());
		taskExec.setMaNm(org.getMaName());
		taskExec.setWorkflowPhaseExecPkey(workflowPhaseExec.getpKey());
		taskExec.setWorkflowPhaseName(workflowPhaseExec.getWorkflowPhaseName());
		taskExec.setPhaseName(RouterConstants.phaseIdNameHash.get(taskTemplate.getPhaseId()));
		if (taskTemplate.getPredessorPkey() != null)
			taskExec.setPredecessorTaskId(WorkflowCacheBuilder.getInstance().getTaskId(taskTemplate.getPredessorPkey()));
		taskExec.setProductivitySet(taskTemplate.isProductivitySet());
		taskExec.setPsid(org.getPsidNo());
		taskExec.setQueueName(RouterConstants.queueIdNameHash.get(taskTemplate.getQueueId()));
		taskExec.setRgnNm(org.getRgnName());
		taskExec.setSlaExec(taskTemplate.getSla());
		taskExec.setSlaMetrics(taskTemplate.getSlaMetrics());
		taskExec.setSlaPickupInMin(taskTemplate.getSlaPickup());
		if (taskTemplate.getSuccessorPkey() != null)
			taskExec.setSuccessorTaskId(WorkflowCacheBuilder.getInstance().getTaskId(taskTemplate.getSuccessorPkey()));
		taskExec.setTaskDesc(taskTemplate.getTaskDesc());
		taskExec.setTaskId(taskTemplate.getTaskId());
		taskExec.setTaskNm(taskTemplate.getTaskName());
		taskExec.setTaskType(RouterConstants.taskIdNameHash.get(taskTemplate.getTaskType()));
		taskExec.setUiBean(taskTemplate.getUiBean());
		taskExec.setWorkorderId(workflowPhaseExec.getWorkorderId());
		taskExec.setRolePkey(taskTemplate.getRolePkey());
		taskExec.setWorkGroupPkey(taskTemplate.getWorkgroupPkey());
		taskExec.setOrgLevel(taskTemplate.getOrgLevel());
		return taskExec;
	}
	
	public void getUdefTaskExec(UDefFormTemplate udefFormTemplate){
		
	}

	public SubTaskExec getSubTaskExec(TaskExec taskExec, SubTaskTemplate subtaskTemplate, Org org) {
		SubTaskExec subTaskExec = new SubTaskExec();
		subTaskExec.setCwsId(taskExec.getCwsId());
		subTaskExec.setEndPoint(subtaskTemplate.getEndPoint());
		subTaskExec.setGlid(org.getGlidNo());
		subTaskExec.setIgnoreSla(subtaskTemplate.isIgnoreSla());
		subTaskExec.setMaName(org.getMaName());
		String phaseName = RouterConstants.phaseIdNameHash.get(subtaskTemplate.getPhaseId());
		subTaskExec.setPhaseName(phaseName);
		subTaskExec.setProductivitySet(subtaskTemplate.isProductivitySet());
		subTaskExec.setPsid(org.getPsidNo());
		String queueName = RouterConstants.queueIdNameHash.get(subtaskTemplate.getQueueId());
		subTaskExec.setQueueName(queueName);

		subTaskExec.setRegionName(org.getRgnName());
		subTaskExec.setSlaExec(subtaskTemplate.getSlaExec());
		subTaskExec.setSlaMetrics(subtaskTemplate.getSlaMetrics());
		subTaskExec.setSubTaskDesc(subtaskTemplate.getSubTaskDesc());
		subTaskExec.setSubTaskId(subtaskTemplate.getSubTaskId());
		subTaskExec.setSubTaskName(subtaskTemplate.getSubTaskNm());
		subTaskExec.setSubTaskType(RouterConstants.taskIdNameHash.get(subtaskTemplate.getTaskType()));
		subTaskExec.setTaskExecPkey(taskExec.getpKey());
		subTaskExec.setTaskId(taskExec.getTaskId());
		subTaskExec.setTaskName(taskExec.getTaskNm());
		subTaskExec.setUiBean(subtaskTemplate.getUiBean());
		subTaskExec.setWorkorderId(taskExec.getWorkorderId());
		return subTaskExec;
	}
	public UDefTaskExec getUDefTaskExec(TaskExec taskExec,UDefFormTemplate udefFormTemplate){
		UDefTaskExec udefTaskExec=new UDefTaskExec();
		udefTaskExec.setCwsId(taskExec.getCwsId());
//		udefTaskExec.setRolePkey();
//		udefTaskExec.setAssignedTo();
//		udefTaskExec.setAssignedBy(assignedBy);
		udefTaskExec.setAssignedDtTime(new Date());
		udefTaskExec.setMilestoneExecPkey(taskExec.getworkflowPhaseExecPkey());
		udefTaskExec.setUdefTaskType(taskExec.getTaskType());
		udefTaskExec.setFormAttributes(udefFormTemplate.getFormAttrs());
		udefTaskExec.setFormColCount(udefFormTemplate.getFormColCount());
		udefTaskExec.setFormDescription(udefFormTemplate.getFormDesc());
		udefTaskExec.setFormName(udefFormTemplate.getFormTitle());
		udefTaskExec.setMandatory(udefFormTemplate.isMandatory());
		udefTaskExec.setUdefTaskDesc(taskExec.getTaskDesc());
		return udefTaskExec;
		
	}
}