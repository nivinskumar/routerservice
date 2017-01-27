package com.immco.db.controller;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.immco.cache.WorkflowCacheBuilder;
import com.immco.common.DBDC;
import com.immco.common.RouterConstants.TaskType;
import com.immco.db.DbLogger;
import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.UDefFormTemplate;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.TASKTEMPLATECONTROLLER;
import com.immco.db.service.router.TaskTemplateService;
import com.immco.db.service.router.UdefFormTemplateService;

/**
*@formatter:off
*/

@RestController
@RequestMapping("/routerservice/tasktemplate")
public class TasktemplateController extends BaseController
{
	
	@Autowired
	DbLogger dbLogger;
	
	@Autowired
	TaskTemplateService taskTemplateService;
	
	@Autowired
	UdefFormTemplateService udefFormTemplateService;
	
	@RequestMapping(value = TASKTEMPLATECONTROLLER.CREATE_OR_UPDATE_TASKTEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })   
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		DBDC dbdc=new DBDC();
		try
		{
		 DCParam dcParam = fromJSON(json, DCParam.class);
		 switch(dcParam.getCtx()){
		 case TaskTemplate.CREATE_TASKTEMPLATE:{
			 TaskTemplate taskTemplate = parseIPersistable(json, TaskTemplate.class);
			 dbdc=taskTemplateService.createOrUpdateTaskTemplate(taskTemplate,dcParam);
			 if(dbdc.isSuccess())
				 WorkflowCacheBuilder.getInstance().updateTaskCache(taskTemplate);
			 retJson = toJson(dbdc);
			 break;
		 }
		 case TaskTemplate.CREATE_APPROVAL_TASKTEMPLATE:{
			 TaskTemplate taskTemplate = parseIPersistable(json, TaskTemplate.class);
			 dbdc=taskTemplateService.createOrUpdateTaskTemplate(taskTemplate,dcParam);
			 WorkflowCacheBuilder.getInstance().updateTaskCache(taskTemplate);
				 TaskTemplate taskTemplateTrue=new TaskTemplate();
				 TaskTemplate taskTemplateFalse=new TaskTemplate();
				 
				 taskTemplateTrue.setPredessorPkey(taskTemplate.getpKey());
				 taskTemplateTrue.setTaskId(taskTemplate.getTaskId()+"T");
				 taskTemplateTrue.setTaskName(taskTemplate.getTaskName()+"-TRUE");
				 taskTemplateTrue.setPhaseId(taskTemplate.getPhaseId());
				 taskTemplateTrue.setQueueId(taskTemplate.getQueueId());
				 taskTemplateTrue.setTaskType(TaskType.HUMAN_TASK);
				 taskTemplateTrue.setTaskDesc(taskTemplate.getTaskDesc());
				 taskTemplateTrue.setSeqNo(taskTemplate.getSeqNo()+1);
				 taskTemplateTrue.setOrgLevel(taskTemplate.getOrgLevel());
				 taskTemplateTrue.setRolePkey(taskTemplate.getRolePkey());
				 taskTemplateTrue.setIgnoreSla(taskTemplate.isIgnoreSla());
				 taskTemplateTrue.setMilestonePkey(taskTemplate.getMilestonePkey());
				 taskTemplateTrue.setProductivitySet(taskTemplate.isProductivitySet());
				 taskTemplateTrue.setWorkgroupPkey(taskTemplate.getWorkgroupPkey());
				 
				 
				 taskTemplateFalse.setPredessorPkey(taskTemplate.getpKey());
				 taskTemplateFalse.setTaskId(taskTemplate.getTaskId()+"F");
				 taskTemplateFalse.setTaskName(taskTemplate.getTaskName()+"-FALSE");
				 taskTemplateFalse.setSeqNo(taskTemplate.getSeqNo()+2);
				 taskTemplateFalse.setPhaseId(taskTemplate.getPhaseId());
				 taskTemplateFalse.setQueueId(taskTemplate.getQueueId());
				 taskTemplateFalse.setTaskType(TaskType.HUMAN_TASK);
				 taskTemplateFalse.setTaskDesc(taskTemplate.getTaskDesc());
				 taskTemplateFalse.setOrgLevel(taskTemplate.getOrgLevel());
				 taskTemplateFalse.setRolePkey(taskTemplate.getRolePkey());
				 taskTemplateFalse.setIgnoreSla(taskTemplate.isIgnoreSla());
				 taskTemplateFalse.setMilestonePkey(taskTemplate.getMilestonePkey());
				 taskTemplateFalse.setProductivitySet(taskTemplate.isProductivitySet());
				 taskTemplateFalse.setWorkgroupPkey(taskTemplate.getWorkgroupPkey());
				 
				 
				 taskTemplateService.createOrUpdateTaskTemplate(taskTemplateTrue,dcParam);
				 WorkflowCacheBuilder.getInstance().updateTaskCache(taskTemplateTrue);
				 taskTemplateService.createOrUpdateTaskTemplate(taskTemplateFalse,dcParam);
				 WorkflowCacheBuilder.getInstance().updateTaskCache(taskTemplateFalse);
				 
				 taskTemplate.setExecTaskTrue(taskTemplateTrue.getTaskId());
				 taskTemplate.setExecTaskFalse(taskTemplateFalse.getTaskId());
				 dbdc= taskTemplateService.createOrUpdateTaskTemplate(taskTemplate,dcParam);
			 if(dbdc.isSuccess())
				 dbdc.setInfoMsg(taskTemplate.getpKey().toString());
				 WorkflowCacheBuilder.getInstance().updateTaskCache(taskTemplate);
			 retJson = toJson(dbdc);
			 break;
		 }
		 case TaskTemplate.CREATE_CONDITION_TASKTEMPLATE:{
			 TaskTemplate taskTemplate = parseIPersistable(json, TaskTemplate.class);
			 dbdc=taskTemplateService.createOrUpdateTaskTemplate(taskTemplate,dcParam);
			 WorkflowCacheBuilder.getInstance().updateTaskCache(taskTemplate);
				 TaskTemplate taskTemplateTrue=new TaskTemplate();
				 TaskTemplate taskTemplateFalse=new TaskTemplate();
				 
				 taskTemplateTrue.setPredessorPkey(taskTemplate.getpKey());
				 taskTemplateTrue.setTaskId(taskTemplate.getTaskId()+"T");
				 taskTemplateTrue.setTaskName(taskTemplate.getTaskName()+"-TRUE");
				 taskTemplateTrue.setPhaseId(taskTemplate.getPhaseId());
				 taskTemplateTrue.setQueueId(taskTemplate.getQueueId());
				 taskTemplateTrue.setTaskType(TaskType.HUMAN_TASK);
				 taskTemplateTrue.setTaskDesc(taskTemplate.getTaskDesc());
				 taskTemplateTrue.setSeqNo(taskTemplate.getSeqNo()+1);
				 taskTemplateTrue.setOrgLevel(taskTemplate.getOrgLevel());
				 taskTemplateTrue.setRolePkey(taskTemplate.getRolePkey());
				 taskTemplateTrue.setIgnoreSla(taskTemplate.isIgnoreSla());
				 taskTemplateTrue.setMilestonePkey(taskTemplate.getMilestonePkey());
				 taskTemplateTrue.setProductivitySet(taskTemplate.isProductivitySet());
				 taskTemplateTrue.setWorkgroupPkey(taskTemplate.getWorkgroupPkey());
				 taskTemplateTrue.setConditionalParentPkey(taskTemplate.getpKey());
				 taskTemplateTrue.setConditionalChild(true);
				 
				 taskTemplateFalse.setConditionalChild(true);
				 taskTemplateFalse.setConditionalParentPkey(taskTemplate.getpKey());
				 taskTemplateFalse.setPredessorPkey(taskTemplate.getpKey());
				 taskTemplateFalse.setTaskId(taskTemplate.getTaskId()+"F");
				 taskTemplateFalse.setTaskName(taskTemplate.getTaskName()+"-FALSE");
				 taskTemplateFalse.setSeqNo(taskTemplate.getSeqNo()+2);
				 taskTemplateFalse.setPhaseId(taskTemplate.getPhaseId());
				 taskTemplateFalse.setQueueId(taskTemplate.getQueueId());
				 taskTemplateFalse.setTaskType(TaskType.HUMAN_TASK);
				 taskTemplateFalse.setTaskDesc(taskTemplate.getTaskDesc());
				 taskTemplateFalse.setOrgLevel(taskTemplate.getOrgLevel());
				 taskTemplateFalse.setRolePkey(taskTemplate.getRolePkey());
				 taskTemplateFalse.setIgnoreSla(taskTemplate.isIgnoreSla());
				 taskTemplateFalse.setMilestonePkey(taskTemplate.getMilestonePkey());
				 taskTemplateFalse.setProductivitySet(taskTemplate.isProductivitySet());
				 taskTemplateFalse.setWorkgroupPkey(taskTemplate.getWorkgroupPkey());
				 
				 
				 taskTemplateService.createOrUpdateTaskTemplate(taskTemplateTrue,dcParam);
				 WorkflowCacheBuilder.getInstance().updateTaskCache(taskTemplateTrue);
				 taskTemplateService.createOrUpdateTaskTemplate(taskTemplateFalse,dcParam);
				 WorkflowCacheBuilder.getInstance().updateTaskCache(taskTemplateFalse);
				 List<BigDecimal> childPkeyList=new ArrayList<>();
				 childPkeyList.add(taskTemplateTrue.getpKey());
				 childPkeyList.add(taskTemplateFalse.getpKey());
				 taskTemplate.setConditionalChildPkey(toJson(childPkeyList));
				 taskTemplate.setExecTaskTrue(taskTemplateTrue.getTaskId());
				 taskTemplate.setExecTaskFalse(taskTemplateFalse.getTaskId());
				 dbdc= taskTemplateService.createOrUpdateTaskTemplate(taskTemplate,dcParam);
			 if(dbdc.isSuccess())
				 dbdc.setInfoMsg(taskTemplate.getpKey().toString());
				 WorkflowCacheBuilder.getInstance().updateTaskCache(taskTemplate);
			 retJson = toJson(dbdc);
			 break;
		 }
		 
		 case TaskTemplate.ASSOCIATE_UDEF_FORMTEMPLATE:{
			 dbdc=taskTemplateService.associateUdefFormTemplate(dcParam);
			 BigDecimal udefPkey = new BigDecimal(dcParam.getpMap().get(UDefFormTemplate.PKEY).toString());
			 UDefFormTemplate formTemplate = udefFormTemplateService.findUdefFormTemplateByPkey(udefPkey);
			 WorkflowCacheBuilder.instance.updateUdefFormCache(formTemplate);
			 retJson = toJson(dbdc);
			 break;
		 }
		 default:break;
		 }
			
		} catch (Exception e)
		{
			retJson=dbLogger.logErrorToJson(e, TasktemplateController.class);
		}
		return retJson;
	} 

	@RequestMapping(value = TASKTEMPLATECONTROLLER.FIND_TASKTEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })    
	@ResponseBody
	public String findTasktemplate(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			TaskTemplate taskTemplate = taskTemplateService.findTaskTemplate(dcParam);
			retJson= toJson(taskTemplate);
		}
		catch(Exception e)
		{
			retJson=dbLogger.logErrorToJson(e, TasktemplateController.class);
		}
		return retJson;
	}

	@RequestMapping(value = TASKTEMPLATECONTROLLER.DELETE_TASKTEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })  
	@ResponseBody
	public String deleteTasktemplate(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			BigDecimal pKey=new BigDecimal(dcParam.getpMap().get(TaskTemplate.PKEY).toString());
			DBDC dbDc=taskTemplateService.deleteTaskTemplate(dcParam);
			if(dbDc.isSuccess())
				WorkflowCacheBuilder.getInstance().removeTask(pKey);
			retJson = toJson(dbDc);
		} catch (Exception e)
		{
			retJson=dbLogger.logErrorToJson(e, TasktemplateController.class);
		}
		return retJson;
	}   
}
