package com.immco.db.service.router;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.dao.router.TaskTemplateDao;
import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.UDefFormTemplate;
import com.immco.db.model.router.templates.WorkflowPhase;
import com.immco.db.proxy.DCParam;

@Service("taskTemplateService")
@Transactional
public class TaskTemplateServiceImpl implements TaskTemplateService {

	@Autowired
	private TaskTemplateDao taskTemplateDao;

	@Autowired
	private DbLogger dbLogger;

	@Override
	public DBDC createOrUpdateTaskTemplate(TaskTemplate taskTemplate,DCParam dcParam) {
		DBDC dbdc = new DBDC();
		try {
			// if (taskTemplate.getpKey() == null) {
			BigDecimal workflowPkey=new BigDecimal(dcParam.getpMap().get(WorkflowPhase.WORKFLOW_PKEY).toString());
			TaskTemplate taskTemplateByID = taskTemplateDao.findTaskTemplateByID(taskTemplate.getTaskId(),
					taskTemplate.getpKey());
			 int templateByName = taskTemplateDao.findTaskTemplateByName(taskTemplate.getTaskName(),
					taskTemplate.getpKey(),workflowPkey);
			if (taskTemplateByID != null) {
				dbdc.setErrorMsg("Task ID Already Exists");
			} else if (templateByName>0) {
				dbdc.setErrorMsg("Task Name Already Exists");
			} else if (taskTemplate.getpKey() == null) {
				taskTemplateDao.createTaskTemplate(taskTemplate);
				dbdc.setSuccessMsg("Task Template "+taskTemplate.getTaskName()+" created");
				dbdc.setInfoMsg(taskTemplate.getpKey().toString());
			} else {
				taskTemplateDao.updateTaskTemplate(taskTemplate);
				dbdc.setSuccessMsg("Task Template "+taskTemplate.getTaskName()+" updated");
				dbdc.setInfoMsg(taskTemplate.getpKey().toString());
			}

		} catch (Exception e) {
			dbLogger.logErrorToJson(e, TaskTemplateServiceImpl.class);
		}
		return dbdc;
	}


	@Override
	public TaskTemplate findTaskTemplate(DCParam dcParam) {
		BigDecimal pKey = new BigDecimal(dcParam.getpMap().get(TaskTemplate.PKEY).toString());
		TaskTemplate taskTemplate = taskTemplateDao.findTaskTemplateByPK(pKey);
		return taskTemplate;
	}

	@Override
	public List<TaskTemplate> findAllTaskTemplates() {
		return taskTemplateDao.findAllTaskTemplates();
	}

	@Override
	public UDefFormTemplate findUdefFormTemplate(BigDecimal taskTemplatePkey) {
		UDefFormTemplate findUdefFormTemplate = taskTemplateDao.findUdefFormTemplate(taskTemplatePkey);
		return findUdefFormTemplate;
	}

//	@Override
//	public DBDC createOrUpdateTemplate(UDefFormTemplate parseIPersistable) {
//		DBDC dbdc = new DBDC();
//		BigDecimal pKey = parseIPersistable.getpKey();
//		taskTemplateDao.createOrUpdateTemplate(parseIPersistable);
//		if(pKey==null){
//		dbdc.setSuccessMsg("Form Template Created");
//		}
//		else
//		{
//			dbdc.setSuccessMsg("Form Template Updated");	
//		}
//		return dbdc;
//	}

	@Override
	public DBDC deleteTaskTemplate(DCParam dcParam) {
		DBDC dbdc=new DBDC();
		BigDecimal pKey=new BigDecimal(dcParam.getpMap().get(TaskTemplate.PKEY).toString());
		try {
			int templateCount = taskTemplateDao.checkUdefFromAssociation(pKey);
			int milestoneAssociation = taskTemplateDao.checkMilestoneAssociation(pKey);
			if(templateCount>0){
				dbdc.setErrorMsg(" Task is associated to "+templateCount+" Template(s)");
			}else if(milestoneAssociation>0){
				dbdc.setErrorMsg(" Task is associated to "+milestoneAssociation+" Milestone(s)");
			}
			else{
			taskTemplateDao.deleteTaskTemplate(pKey);
			dbdc.setSuccessMsg("Task Deleted");
			}
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, TaskTemplateServiceImpl.class);
		}
		return dbdc;
	}

//	@Override
//	public UDefFormTemplate findUdefFormTemplateByPkey(BigDecimal pKey) {
//		UDefFormTemplate findUdefFormTemplate = taskTemplateDao.findUdefFormTemplateByPkey(pKey);
//		return findUdefFormTemplate;
//	}

	@Override
	public DBDC associateUdefFormTemplate(DCParam dcParam) {
		
		DBDC dbdc=new DBDC();
		try {
			BigDecimal taskPkey = new BigDecimal(dcParam.getpMap().get(UDefFormTemplate.TASK_TEMPLATE_PKEY).toString());
			BigDecimal UdefPkey = new BigDecimal(dcParam.getpMap().get(UDefFormTemplate.PKEY).toString());
			taskTemplateDao.associateUdefForm(taskPkey, UdefPkey);
			dbdc.setSuccessMsg("Form Templated Associated");
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, TaskTemplateServiceImpl.class);
		}
		return dbdc;
	}

}
