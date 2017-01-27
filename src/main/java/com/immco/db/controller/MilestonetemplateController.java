package com.immco.db.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.immco.cache.WorkflowCacheBuilder;
import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.model.router.templates.Milestones;
import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.WorkflowPhase;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.MILESTONETEMPLATECONTROLLER;
import com.immco.db.service.router.WorkflowPhaseService;

/**
*@formatter:off
*/

@RestController
@RequestMapping("/routerservice/milestonetemplate")
public class MilestonetemplateController extends BaseController
{
	@Autowired
	WorkflowPhaseService milestoneTemplateService;
	
	@Autowired
	DbLogger dbLogger;
	
	@RequestMapping(value = MILESTONETEMPLATECONTROLLER.CREATE_OR_UPDATE_MILESTONETEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })   
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json)
	{
		DBDC dbDc=new DBDC();
		String retJson = defaultErrMsg();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			switch(dcParam.getCtx()){
			case WorkflowPhase.CREATE_WORKFLOWPHASE:{
				WorkflowPhase milestoneTemplate = parseIPersistable(json, WorkflowPhase.class);
				dbDc = milestoneTemplateService.createMilesoneTemplate(milestoneTemplate);
				retJson = toJson(dbDc);
				if(dbDc.isSuccess())
					WorkflowCacheBuilder.getInstance().updateWorkflowPhaseCache(milestoneTemplate);
				break;
			}
			case WorkflowPhase.UPDATE_WORKFLOWPHASE:{
				WorkflowPhase milestoneTemplate = parseIPersistable(json, WorkflowPhase.class);
				dbDc = milestoneTemplateService.updateMilesoneTemplate(milestoneTemplate);
				retJson=toJson(dbDc);
				if(dbDc.isSuccess())
					WorkflowCacheBuilder.getInstance().updateWorkflowPhaseCache(milestoneTemplate);
				break;
			}
			case TaskTemplate.ASSOCIATE_UDEF_MILESTONES:{
				dbDc=milestoneTemplateService.associateMilestoneAndTasks(dcParam);
				retJson=toJson(dbDc);
				break;
			}
			case Milestones.CREATE_OR_UPDATE_MILESTONE:{
				Milestones milestones = parseIPersistable(json, Milestones.class);
				dbDc=milestoneTemplateService.createMilestones(milestones);
				retJson=toJson(dbDc);
				break;
			}
			default : break;
			}
		
		} catch (Exception e)
		{
			retJson=dbLogger.logErrorToJson(e, MilestonetemplateController.class);
		}
		return retJson;
	} 

	@RequestMapping(value = MILESTONETEMPLATECONTROLLER.FIND_MILESTONETEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })    
	@ResponseBody
	public String findMilestonetemplate(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			if(dcParam.getCtx().equals(Milestones.FIND_MILESTONE_BY_PKEY)){
				Milestones milestones = milestoneTemplateService.findMilestones(dcParam);
				retJson= toJson(milestones);
			}else{
				WorkflowPhase milestoneTemplate = milestoneTemplateService.findMilestoneTemplate(dcParam);
				retJson= toJson(milestoneTemplate);
			}
		}
		catch(Exception e)
		{
			retJson=dbLogger.logErrorToJson(e, MilestonetemplateController.class);
		}
		return retJson;
	}

	@RequestMapping(value = MILESTONETEMPLATECONTROLLER.DELETE_MILESTONETEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })  
	@ResponseBody
	public String deleteMilestonetemplate(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		DBDC dbDc=new DBDC();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			if(dcParam.getCtx().equalsIgnoreCase(Milestones.DELETE_MILESTONE)){
				dbDc = milestoneTemplateService.deleteMilestones(dcParam);
			}else{
			BigDecimal pKey=new BigDecimal(dcParam.getpMap().get(WorkflowPhase.PKEY).toString());
			dbDc=milestoneTemplateService.deleteMilestonetemplate(dcParam);
			if(dbDc.isSuccess())
				WorkflowCacheBuilder.getInstance().removeWorkflowPhase(pKey);
			}
			retJson = toJson(dbDc);
		} catch (Exception e)
		{
			retJson=dbLogger.logErrorToJson(e, MilestonetemplateController.class);
		}
		return retJson;
	}   
}
