package com.immco.cache;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.templates.SubTaskTemplate;
import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.UDefFormTemplate;
import com.immco.db.model.router.templates.WorkFlowTemplate;
import com.immco.db.model.router.templates.WorkflowPhase;
import com.immco.db.proxy.ConstructionServiceProxy;
import com.immco.routing.utils.LogCtx;
import com.immco.routing.utils.LoggerService;
import com.immco.startup.IStarter;
import com.immco.startup.ServiceKickStarter;

/**
 * Cache Layer for the Workflows
 * 
 * @author shajeelawrence
 *
 */
public enum WorkflowCacheBuilder implements IStarter {

	instance;

	private ServiceKickStarter serviceKickStarter;
	private boolean started = false;

	private HashMap<Long, WorkFlowTemplate> conTypeWfTemplateMap = new HashMap<>();
	private HashMap<String, WorkFlowTemplate> mapOfWorkflowTemplate = new HashMap<>();
	private HashMap<String, WorkflowPhase> mapOfWorkflowPhase = new HashMap<>();
	private HashMap<String, TaskTemplate> mapOfTaskTemplate = new HashMap<>();
	private HashMap<String, SubTaskTemplate> mapOfSubTaskTemplate = new HashMap<>();
	private HashMap<String, UDefFormTemplate> mapOfUdefFormTemplate = new HashMap<>();

	private HashMap<Long, WorkFlowTemplate> pkeyMapOfWorkflowTemplate = new HashMap<>();
	private HashMap<Long, WorkflowPhase> pkeyMapOfWorkflowPhase = new HashMap<>();
	private HashMap<Long, TaskTemplate> pkeyMapOfTaskTemplate = new HashMap<>();
	private HashMap<Long, SubTaskTemplate> pkeyMapOfSubTaskTemplate = new HashMap<>();
	private HashMap<Long, UDefFormTemplate> pkeyMapOfUdefFormTemplate = new HashMap<>();
	private HashMap<Long, Integer> formTemplateMap = new HashMap<Long, Integer>();

	private HashMap<Long, String> constructionTypeMap = new HashMap<>();

	public static final WorkflowCacheBuilder getInstance() {
		return instance;
	}

	@Override
	public boolean start() {

		boolean started = true;

		List<WorkFlowTemplate> workflowTemplatesList = serviceKickStarter.getWorkflowTemplateService().getAllWorkflowTemplates();
		List<WorkflowPhase> workflowPhaseList = serviceKickStarter.getMilestoneTemplateService().getAllMilestones();

		List<TaskTemplate> taskTemplatesList = serviceKickStarter.getTaskTemplateService().findAllTaskTemplates();
		List<SubTaskTemplate> subtaskTemplatesList = serviceKickStarter.getSubTaskTemplateService().findAllSubTaskTemplates();

		List<UDefFormTemplate> allUdefFormTemplates = serviceKickStarter.getUdefFormTemplateService().getAllUdefFormTemplates();

		for (WorkFlowTemplate workFlowTemplate : workflowTemplatesList) {
			long conTypePkey = workFlowTemplate.getConstructionTypePkey().longValue();
			this.conTypeWfTemplateMap.put(conTypePkey, workFlowTemplate);
			this.mapOfWorkflowTemplate.put(workFlowTemplate.getWorkflowId(), workFlowTemplate);
			this.pkeyMapOfWorkflowTemplate.put(workFlowTemplate.getpKey().longValue(), workFlowTemplate);
		}

		for (WorkflowPhase workflowPhase : workflowPhaseList) {
			this.mapOfWorkflowPhase.put(workflowPhase.getWorkflowPhaseId(), workflowPhase);
			this.pkeyMapOfWorkflowPhase.put(workflowPhase.getpKey().longValue(), workflowPhase);
		}

		for (TaskTemplate taskTemplate : taskTemplatesList) {
			this.mapOfTaskTemplate.put(taskTemplate.getTaskId(), taskTemplate);
			this.pkeyMapOfTaskTemplate.put(taskTemplate.getpKey().longValue(), taskTemplate);
		}

		for (SubTaskTemplate subTaskTemplate : subtaskTemplatesList) {
			this.mapOfSubTaskTemplate.put(subTaskTemplate.getSubTaskId(), subTaskTemplate);
			this.pkeyMapOfSubTaskTemplate.put(subTaskTemplate.getpKey().longValue(), subTaskTemplate);
		}

		for (UDefFormTemplate udefFormTemplate : allUdefFormTemplates) {
			this.mapOfUdefFormTemplate.put(udefFormTemplate.getpKey() + "", udefFormTemplate);
			this.pkeyMapOfUdefFormTemplate.put(udefFormTemplate.getpKey().longValue(), udefFormTemplate);
		}

		refreshConstructionTypeCache();
		refreshWorkflowTrees();
		printTree();
		this.started = true;
		return this.started;
	}

	public void updateWorkflowCache(WorkFlowTemplate workflowTemplate) {
		if (this.pkeyMapOfWorkflowTemplate.containsKey(workflowTemplate.getpKey().longValue())) {
			WorkFlowTemplate existingWfTemplate = this.pkeyMapOfWorkflowTemplate.get(workflowTemplate.getpKey().longValue());
			this.mapOfWorkflowTemplate.remove(existingWfTemplate.getWorkflowId());
			this.mapOfWorkflowTemplate.put(workflowTemplate.getWorkflowId(), workflowTemplate);
			this.conTypeWfTemplateMap.put(workflowTemplate.getConstructionTypePkey().longValue(), workflowTemplate);
			this.pkeyMapOfWorkflowTemplate.put(workflowTemplate.getpKey().longValue(), workflowTemplate);
			// swap the milestonetemplate array
			workflowTemplate.setMilestoneTemplates(existingWfTemplate.getMilestoneTemplates());
		} else {
			// this is a newly created workflowtemplate
			this.conTypeWfTemplateMap.put(workflowTemplate.getConstructionTypePkey().longValue(), workflowTemplate);
			this.mapOfWorkflowTemplate.put(workflowTemplate.getWorkflowId(), workflowTemplate);
			this.pkeyMapOfWorkflowTemplate.put(workflowTemplate.getpKey().longValue(), workflowTemplate);
		}
	}

	public void updateWorkflowPhaseCache(WorkflowPhase workflowPhase) {
		if (pkeyMapOfWorkflowPhase.containsKey(workflowPhase.getpKey().longValue())) {
			WorkflowPhase existingWorkflowPhase = this.pkeyMapOfWorkflowPhase.get(workflowPhase.getpKey().longValue());
			this.mapOfWorkflowPhase.remove(workflowPhase.getWorkflowPhaseId());
			this.mapOfWorkflowPhase.put(workflowPhase.getWorkflowPhaseId(), workflowPhase);
			this.pkeyMapOfWorkflowPhase.put(workflowPhase.getpKey().longValue(), workflowPhase);
			workflowPhase.setTaskTemplates(existingWorkflowPhase.getTaskTemplates());
		} else {
			// a new milestone
			this.mapOfWorkflowPhase.put(workflowPhase.getWorkflowPhaseId(), workflowPhase);
			this.pkeyMapOfWorkflowPhase.put(workflowPhase.getpKey().longValue(), workflowPhase);

			WorkFlowTemplate workFlowTemplate = pkeyMapOfWorkflowTemplate.get(workflowPhase.getWorkflowTemplatePkey().longValue());
			List<WorkflowPhase> msList = new ArrayList<>(Arrays.asList(workFlowTemplate.getMilestoneTemplates()));
			msList.add(workflowPhase);
			WorkflowPhase[] msArr = new WorkflowPhase[msList.size()];

			for (WorkflowPhase wfPhase : msList) {
				int seqNo = wfPhase.getSeqNo();
				msArr[seqNo] = wfPhase;
				createMileStoneTree(wfPhase.getWorkflowPhaseId());
			}
			workFlowTemplate.setMilestoneTemplates(msArr);
		}
	}

	public void updateTaskCache(TaskTemplate taskTemplate) {
		getUdefFormTaskTemplateMap();
		if (pkeyMapOfTaskTemplate.containsKey(taskTemplate.getpKey().longValue())) {
			TaskTemplate existingTaskTemplat = this.pkeyMapOfTaskTemplate.get(taskTemplate.getpKey().longValue());
			this.mapOfTaskTemplate.remove(taskTemplate.getTaskId());
			this.mapOfTaskTemplate.put(taskTemplate.getTaskId(), taskTemplate);
			this.pkeyMapOfTaskTemplate.put(taskTemplate.getpKey().longValue(), taskTemplate);
			taskTemplate.setSubTaskTemplate(existingTaskTemplat.getSubTaskTemplate());
		} else {

			// a new template
			this.mapOfTaskTemplate.put(taskTemplate.getTaskId(), taskTemplate);
			this.pkeyMapOfTaskTemplate.put(taskTemplate.getpKey().longValue(), taskTemplate);

			WorkflowPhase workflowPhase = pkeyMapOfWorkflowPhase.get(taskTemplate.getMilestonePkey().longValue());
			List<TaskTemplate> tList = new ArrayList<>(Arrays.asList(workflowPhase.getTaskTemplates()));
			// System.out.println(tList);
			tList.add(taskTemplate);
			TaskTemplate[] tArr = new TaskTemplate[tList.size()];

			for (TaskTemplate tTemplate : tList) {
				int seqNo = tTemplate.getSeqNo();
				tArr[seqNo] = tTemplate;
				createTaskTree(tTemplate.getTaskId());
			}

			workflowPhase.setTaskTemplates(tArr);
		}
	}

	public void updateSubTaskCache(SubTaskTemplate subTaskTemplate) {
		if (pkeyMapOfSubTaskTemplate.containsKey(subTaskTemplate.getpKey().longValue())) {
			this.mapOfSubTaskTemplate.remove(subTaskTemplate.getSubTaskId());
			this.mapOfSubTaskTemplate.put(subTaskTemplate.getSubTaskId(), subTaskTemplate);
			this.pkeyMapOfSubTaskTemplate.put(subTaskTemplate.getpKey().longValue(), subTaskTemplate);
		} else {
			// a new subtask
			this.mapOfSubTaskTemplate.put(subTaskTemplate.getSubTaskId(), subTaskTemplate);
			this.pkeyMapOfSubTaskTemplate.put(subTaskTemplate.getpKey().longValue(), subTaskTemplate);

			TaskTemplate taskTemplate = pkeyMapOfTaskTemplate.get(subTaskTemplate.getTaskPkey().longValue());
			List<SubTaskTemplate> stList = new ArrayList<>(Arrays.asList(taskTemplate.getSubTaskTemplate()));
			stList.add(subTaskTemplate);
			SubTaskTemplate[] stArr = new SubTaskTemplate[stList.size()];

			for (SubTaskTemplate stTemplate : stList) {
				int seqNo = stTemplate.getSeqNo();
				stArr[seqNo] = stTemplate;
			}
			taskTemplate.setSubTaskTemplate(stArr);
		}
	}

	public void updateUdefFormCache(UDefFormTemplate formTemplate) {
		if (pkeyMapOfUdefFormTemplate.containsKey(formTemplate.getpKey().longValue())) {
			this.mapOfUdefFormTemplate.remove(formTemplate.getpKey() + "");
			this.mapOfUdefFormTemplate.put(formTemplate.getpKey() + "", formTemplate);
			this.pkeyMapOfUdefFormTemplate.put(formTemplate.getpKey().longValue(), formTemplate);
			// When Associating with form
			getUdefFormTaskTemplateMap();
			Integer taskPkey = formTemplateMap.get(formTemplate.getpKey().longValue());
			if (taskPkey != null) {
				TaskTemplate taskTemplate = pkeyMapOfTaskTemplate.get(taskPkey.longValue());
				List<UDefFormTemplate> udefFormList = new ArrayList<>(Arrays.asList(taskTemplate.getUdefFormTemplate()));
				udefFormList.add(formTemplate);
				UDefFormTemplate[] stArr = new UDefFormTemplate[udefFormList.size()];
				int i = 0;
				for (UDefFormTemplate udefTemplate : udefFormList) {
					stArr[i] = udefTemplate;
					i++;
				}
				taskTemplate.setUdefFormTemplate(stArr);
			}

		} else {
			// a new udef form
			this.mapOfUdefFormTemplate.put(formTemplate.getpKey() + "", formTemplate);
			this.pkeyMapOfUdefFormTemplate.put(formTemplate.getpKey().longValue(), formTemplate);
			// BigDecimal taskPkey =
			// serviceKickStarter.getUdefFormTemplateService().findTaskByUdefFormPkey(formTemplate.getpKey());
		}
	}

	public void removeUdefFormtemplate(BigDecimal udefFormPkey) {
		UDefFormTemplate formTemplate = pkeyMapOfUdefFormTemplate.remove(udefFormPkey.longValue());
		this.mapOfUdefFormTemplate.remove(formTemplate.getpKey() + "");
		Integer taskPkey = formTemplateMap.get(formTemplate.getpKey().longValue());
		TaskTemplate taskTemplate = this.pkeyMapOfTaskTemplate.get(taskPkey.longValue());
		refreshUdefFormMaps();
		getUdefFormTaskTemplateMap();
		createTaskTree(taskTemplate.getTaskId());
	}

	public void removeSubTask(BigDecimal subtaskTemplatePkey) {
		SubTaskTemplate subTaskTemplate = this.pkeyMapOfSubTaskTemplate.remove(subtaskTemplatePkey.longValue());
		this.mapOfSubTaskTemplate.remove(subTaskTemplate.getSubTaskId());
		TaskTemplate taskTemplate = this.pkeyMapOfTaskTemplate.get(subTaskTemplate.getTaskPkey().longValue());
		refreshSubtaskMaps();
		getUdefFormTaskTemplateMap();
		createTaskTree(taskTemplate.getTaskId());
	}

	public void removeTask(BigDecimal taskTemplatePkey) {
		TaskTemplate taskTemplate = this.pkeyMapOfTaskTemplate.remove(taskTemplatePkey.longValue());
		this.mapOfTaskTemplate.remove(taskTemplate.getTaskId());
		WorkflowPhase workflowPhase = this.pkeyMapOfWorkflowPhase.get(taskTemplate.getMilestonePkey().longValue());
		refreshTaskMaps();
		createMileStoneTree(workflowPhase.getWorkflowPhaseId());
	}

	public void removeWorkflowPhase(BigDecimal milestonePkey) {
		WorkflowPhase workflowPhase = this.pkeyMapOfWorkflowPhase.remove(milestonePkey.longValue());
		this.mapOfWorkflowPhase.remove(workflowPhase.getWorkflowPhaseId());
		WorkFlowTemplate workFlowTemplate = this.pkeyMapOfWorkflowTemplate.get(workflowPhase.getWorkflowTemplatePkey().longValue());
		refreshWorkflowPhaseMaps();
		getUdefFormTaskTemplateMap();
		createWorkFlowTree(workFlowTemplate.getWorkflowId());
	}

	public void removeWorkFlow(BigDecimal workflowPkey) {
		WorkFlowTemplate workflowTemplate = this.pkeyMapOfWorkflowTemplate.remove(workflowPkey.longValue());
		this.mapOfWorkflowTemplate.remove(workflowTemplate.getWorkflowId());
		this.conTypeWfTemplateMap.remove(workflowTemplate.getConstructionTypePkey().longValue());
	}

	public void refreshWorkflowTrees() {
		getUdefFormTaskTemplateMap();
		for (Map.Entry<Long, WorkFlowTemplate> map : conTypeWfTemplateMap.entrySet()) {
			createWorkFlowTrees(map.getKey());
		}
	}

	public WorkFlowTemplate createWorkFlowTree(Long constructionTypePkey) {
		WorkFlowTemplate workFlowTemplate = this.conTypeWfTemplateMap.get(constructionTypePkey);
		getUdefFormTaskTemplateMap();
		return createWorkFlowTree(workFlowTemplate.getWorkflowId());
	}

	private WorkFlowTemplate createWorkFlowTrees(Long constructionTypePkey) {
		WorkFlowTemplate workFlowTemplate = this.conTypeWfTemplateMap.get(constructionTypePkey);
		return createWorkFlowTree(workFlowTemplate.getWorkflowId());
	}

	private WorkFlowTemplate createWorkFlowTree(String workFlowId) {
		WorkFlowTemplate workFlowTemplate = this.mapOfWorkflowTemplate.get(workFlowId);
		Long wfPkey = workFlowTemplate.getpKey().longValue();

		List<WorkflowPhase> msList = new ArrayList<>();
		for (Map.Entry<String, WorkflowPhase> map : this.mapOfWorkflowPhase.entrySet()) {
			WorkflowPhase workflowPhase = map.getValue();
			if (workflowPhase.getWorkflowTemplatePkey().longValue() == wfPkey) {
				msList.add(workflowPhase);
			}
		}

		WorkflowPhase[] msArr = new WorkflowPhase[msList.size()];

		for (WorkflowPhase workflowPhase : msList) {
			int seqNo = workflowPhase.getSeqNo();
			msArr[seqNo] = workflowPhase;
			createMileStoneTree(workflowPhase.getWorkflowPhaseId());
		}
		workFlowTemplate.setMilestoneTemplates(msArr);

		return workFlowTemplate;
	}

	private WorkflowPhase createMileStoneTree(String milestoneId) {
		WorkflowPhase workflowPhase = this.mapOfWorkflowPhase.get(milestoneId);
		List<TaskTemplate> ttList = new ArrayList<>();

		for (Map.Entry<String, TaskTemplate> map : this.mapOfTaskTemplate.entrySet()) {
			TaskTemplate taskTemplate = map.getValue();
			if (taskTemplate.getMilestonePkey().longValue() == workflowPhase.getpKey().longValue())
				ttList.add(map.getValue());
		}

		TaskTemplate[] ttArr = new TaskTemplate[ttList.size()];

		for (TaskTemplate taskTemplate : ttList) {
			int seqNo = taskTemplate.getSeqNo();
			ttArr[seqNo] = taskTemplate;
			createTaskTree(taskTemplate.getTaskId());
		}

		workflowPhase.setTaskTemplates(ttArr);

		return workflowPhase;
	}

	private TaskTemplate createTaskTree(String taskId) {
		TaskTemplate taskTemplae = this.mapOfTaskTemplate.get(taskId);
		List<SubTaskTemplate> stList = new ArrayList<>();
		List<UDefFormTemplate> udList = new ArrayList<>();
		Integer taskPkey = null;

		for (Map.Entry<String, SubTaskTemplate> map : this.mapOfSubTaskTemplate.entrySet()) {
			SubTaskTemplate subTaskTemplate = map.getValue();
			if (subTaskTemplate.getTaskPkey().longValue() == taskTemplae.getpKey().longValue())
				stList.add(subTaskTemplate);
		}

		SubTaskTemplate[] stArr = new SubTaskTemplate[stList.size()];

		for (SubTaskTemplate subTaskTemplate : stList) {
			int seqNo = subTaskTemplate.getSeqNo();
			stArr[seqNo] = subTaskTemplate;
		}
		taskTemplae.setSubTaskTemplate(stArr);

		try {
			for (Map.Entry<String, UDefFormTemplate> map : this.mapOfUdefFormTemplate.entrySet()) {
				UDefFormTemplate udefFormTemplate = map.getValue();

				// List<?> list;
				// list = constructionServiceProxy.executePlainSelect("ROUTER",
				// "SELECT TASKTEMPLATE_PKEY FROM
				// ROUTER.TASKTEMPLATE_UDEF_FORM_MAP WHERE
				// UDEF_FORM_TEMPLATE_PKEY=" + udefFormTemplate.getpKey());
				// for (Object object : list) {
				// List<?> row = (List<?>) object;
				// taskPkey = (Integer) row.get(0);
				// }
				taskPkey = formTemplateMap.get(udefFormTemplate.getpKey().longValue());

				if (taskPkey != null) {
					if (taskPkey.longValue() == taskTemplae.getpKey().longValue())
						udList.add(udefFormTemplate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UDefFormTemplate[] udArr = new UDefFormTemplate[udList.size()];
		int i = 0;
		for (UDefFormTemplate udefTemplate : udList) {
			udArr[i] = udefTemplate;
			i++;
		}
		taskTemplae.setUdefFormTemplate(udArr);
		return taskTemplae;
	}

	private void getUdefFormTaskTemplateMap() {

		System.out.println("Fetching form-Task Map");
		formTemplateMap = new HashMap<Long, Integer>();

		Integer taskPkey = null;
		Integer formPkey = null;
		ConstructionServiceProxy constructionServiceProxy = this.serviceKickStarter.getConstructionServiceProxy();
		try {
			List<?> list = constructionServiceProxy.executePlainSelect("ROUTER", "SELECT UDEF_FORM_TEMPLATE_PKEY,TASKTEMPLATE_PKEY FROM ROUTER.TASKTEMPLATE_UDEF_FORM_MAP ");
			for (Object object : list) {
				List<?> row = (List<?>) object;
				formPkey = (Integer) row.get(0);
				taskPkey = (Integer) row.get(1);
				formTemplateMap.put(formPkey.longValue(), taskPkey);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean stop() {
		this.started = false;

		return true;
	}

	@Override
	public boolean getStatus() {
		return this.started;
	}

	@Override
	public String getServiceName() {
		return instance.getClass().getName();
	}

	public void setServiceKickStarter(ServiceKickStarter serviceKickStarter) {
		this.serviceKickStarter = serviceKickStarter;
	}

	public void printTree() {

		String tab = "----";
		String CR = "\n";
		for (Map.Entry<String, WorkFlowTemplate> map : this.mapOfWorkflowTemplate.entrySet()) {

			String wfTemplateId = map.getKey();
			WorkFlowTemplate workflowTemplate = map.getValue();
			StringBuffer sb = new StringBuffer("+ " + wfTemplateId + "(" + workflowTemplate.getWorkflowName() + ")").append(CR);
			WorkflowPhase[] workflowPhases = workflowTemplate.getMilestoneTemplates();
			for (WorkflowPhase workflowPhase : workflowPhases) {
				sb.append(tab + "+ ").append(workflowPhase.getWorkflowPhaseId() + "(" + workflowPhase.getWorkflowPhaseName() + ")").append(CR);
				TaskTemplate[] taskTemplates = workflowPhase.getTaskTemplates();
				for (TaskTemplate taskTemplate : taskTemplates) {
					sb.append(tab + tab).append("+ ").append(taskTemplate.getTaskId() + "(" + taskTemplate.getTaskName() + ")").append(CR);
					SubTaskTemplate[] subTaskTemplates = taskTemplate.getSubTaskTemplate();
					for (SubTaskTemplate subTaskTemplate : subTaskTemplates) {
						sb.append(tab + tab + tab + " ").append(subTaskTemplate.getSubTaskId() + "(" + subTaskTemplate.getSubTaskNm() + ")").append(CR);
					}
					UDefFormTemplate[] udefFormTemplates = taskTemplate.getUdefFormTemplate();
					for (UDefFormTemplate udefFormTemplate : udefFormTemplates) {
						if (udefFormTemplate != null) {
							sb.append(tab + tab + tab + " ").append(udefFormTemplate.getpKey() + "(" + udefFormTemplate.getFormTitle() + ")").append(CR);
						}
					}

				}
			}
			System.out.println(sb.toString());
		}
	}

	private void refreshConstructionTypeCache() {
		try {
			ConstructionServiceProxy constructionServiceProxy = this.serviceKickStarter.getConstructionServiceProxy();
			List<?> list = constructionServiceProxy.executePlainSelect("ROUTER", "SELECT PKEY, CONSTRUCTION_TYPE_NM FROM CONSTRUCTION_TYPE");
			for (Object object : list) {
				List<?> row = (List<?>) object;
				Number pkey = (Number) row.get(0);
				String constructionType = (String) row.get(1);
				this.constructionTypeMap.put(pkey.longValue(), constructionType);
			}

			LoggerService.getInstance().warning(LogCtx.WORKFLOW_CACHE, "Consstruction Type Cached..", WorkflowCacheBuilder.class);
		} catch (Exception e) {

			Throwable rootCause = ExceptionUtils.getRootCause(e);
			if (rootCause instanceof ConnectException) {
				LoggerService.getInstance().error(LogCtx.WORKFLOW_CACHE, "Could not Connect to Construction Service....Waiting for Service to come up..", WorkflowCacheBuilder.class);

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				refreshConstructionTypeCache();
			}
		}
	}

	private void refreshUdefFormMaps() {
		List<UDefFormTemplate> allUdefFormTemplates = serviceKickStarter.getUdefFormTemplateService().getAllUdefFormTemplates();
		mapOfUdefFormTemplate.clear();
		pkeyMapOfUdefFormTemplate.clear();
		for (UDefFormTemplate uDefFormTemplate : allUdefFormTemplates) {
			mapOfUdefFormTemplate.put(uDefFormTemplate.getpKey() + "", uDefFormTemplate);
			pkeyMapOfUdefFormTemplate.put(uDefFormTemplate.getpKey().longValue(), uDefFormTemplate);
		}
	}

	private void refreshSubtaskMaps() {
		List<SubTaskTemplate> subtaskTemplatesList = serviceKickStarter.getSubTaskTemplateService().findAllSubTaskTemplates();
		mapOfSubTaskTemplate.clear();
		pkeyMapOfSubTaskTemplate.clear();
		for (SubTaskTemplate subTaskTemplate : subtaskTemplatesList) {
			this.mapOfSubTaskTemplate.put(subTaskTemplate.getSubTaskId(), subTaskTemplate);
			this.pkeyMapOfSubTaskTemplate.put(subTaskTemplate.getpKey().longValue(), subTaskTemplate);
		}
	}

	private void refreshTaskMaps() {
		List<TaskTemplate> taskTemplatesList = serviceKickStarter.getTaskTemplateService().findAllTaskTemplates();
		mapOfTaskTemplate.clear();
		pkeyMapOfTaskTemplate.clear();
		for (TaskTemplate taskTemplate : taskTemplatesList) {
			this.mapOfTaskTemplate.put(taskTemplate.getTaskId(), taskTemplate);
			this.pkeyMapOfTaskTemplate.put(taskTemplate.getpKey().longValue(), taskTemplate);
		}
	}

	private void refreshWorkflowPhaseMaps() {
		List<WorkflowPhase> workflowPhasesList = serviceKickStarter.getMilestoneTemplateService().getAllMilestones();
		mapOfWorkflowPhase.clear();
		pkeyMapOfWorkflowPhase.clear();
		for (WorkflowPhase workflowPhase : workflowPhasesList) {
			this.mapOfWorkflowPhase.put(workflowPhase.getWorkflowPhaseId(), workflowPhase);
			this.pkeyMapOfWorkflowPhase.put(workflowPhase.getpKey().longValue(), workflowPhase);
		}
	}

	// private void refreshWorkflowMaps(){
	// List<WorkFlowTemplate> workflowTemplatesList =
	// serviceKickStarter.getWorkflowTemplateService().getAllWorkflowTemplates();
	// mapOfWorkflowTemplate.clear();
	// pkeyMapOfWorkflowTemplate.clear();
	// for (WorkFlowTemplate workFlowTemplate : workflowTemplatesList) {
	//// long conTypePkey =
	// workFlowTemplate.getConstructionTypePkey().longValue();
	//// this.conTypeWfTemplateMap.put(conTypePkey, workFlowTemplate);
	// this.mapOfWorkflowTemplate.put(workFlowTemplate.getWorkflowId(),
	// workFlowTemplate);
	// this.pkeyMapOfWorkflowTemplate.put(workFlowTemplate.getpKey().longValue(),
	// workFlowTemplate);
	// }
	//
	// }
	public String getTaskId(BigDecimal taskPkey) {
		TaskTemplate taskTemplate = this.pkeyMapOfTaskTemplate.get(taskPkey.longValue());
		return taskTemplate == null ? null : taskTemplate.getTaskId();
	}

	public TaskTemplate getTaskTemplate(BigDecimal taskPkey) {
		TaskTemplate taskTemplate = this.pkeyMapOfTaskTemplate.get(taskPkey.longValue());
		return taskTemplate;
	}

	public String getConstructionType(BigDecimal constructionTypePkey) {
		return this.constructionTypeMap.get(constructionTypePkey.longValue());
	}

	public TaskExec getFirstTask(BigDecimal cwsId) {
		return serviceKickStarter.getUdefFormTemplateService().findFirstTaskExec(cwsId);

	}
	public WorkFlowTemplate checkWorflowExistForConsType(Long constructionTypePkey){
		WorkFlowTemplate workFlowTemplate = conTypeWfTemplateMap.get(constructionTypePkey);
		return workFlowTemplate;
	}
}
