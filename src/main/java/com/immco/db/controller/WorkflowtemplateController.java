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
import com.immco.db.model.router.templates.WorkFlowTemplate;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.WORKFLOWTEMPLATECONTROLLER;
import com.immco.db.service.router.WorkflowTemplateService;

/**
*@formatter:off
*/

@RestController
@RequestMapping("/routerservice/workflowtemplate")
public class WorkflowtemplateController extends BaseController
{
	@Autowired
	private WorkflowTemplateService workflowTemplateSerivice;
	
	@Autowired
	DbLogger dbLogger;
	
	@RequestMapping(value = WORKFLOWTEMPLATECONTROLLER.CREATE_OR_UPDATE_WORKFLOWTEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })   
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		
		DBDC dbdc=new DBDC();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			WorkFlowTemplate workFlowTemplate = parseIPersistable(json, WorkFlowTemplate.class);
			switch(dcParam.getCtx()){
			case WorkFlowTemplate.CREATE_WORKFLOWTEMPLATE:{
				dbdc=workflowTemplateSerivice.createWorkflowTemplate(workFlowTemplate);
				retJson = toJson(dbdc);
				if(dbdc.isSuccess())
				WorkflowCacheBuilder.getInstance().updateWorkflowCache(workFlowTemplate);
				break;
			}
			case WorkFlowTemplate.UPDATE_WORKFLOWTEMPLATE:{
				dbdc=workflowTemplateSerivice.updateWorkflowTemplate(workFlowTemplate);
				retJson = toJson(dbdc);
				if(dbdc.isSuccess())
				WorkflowCacheBuilder.getInstance().updateWorkflowCache(workFlowTemplate);
				break;
			}
			default:break;
			}
		} catch (Exception e)
		{
			dbLogger.logErrorToJson(e, WorkflowtemplateController.class);
		}
		return retJson;
	} 

	@RequestMapping(value = WORKFLOWTEMPLATECONTROLLER.FIND_WORKFLOWTEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })    
	@ResponseBody
	public String findWorkflowtemplate(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		DBDC dbdc=new DBDC();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			WorkFlowTemplate workflowTemplate = workflowTemplateSerivice.findWorkflowTemplate(dcParam);
			retJson= toJson(workflowTemplate);
		}
		catch(Exception e)
		{
			dbLogger.logErrorToJson(e, WorkflowtemplateController.class);
		}
		return retJson;
	}

	@RequestMapping(value = WORKFLOWTEMPLATECONTROLLER.DELETE_WORKFLOWTEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })  
	@ResponseBody
	public String deleteWorkflowtemplate(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
		 DCParam dcParam = fromJSON(json, DCParam.class);
			DBDC dbDc=new DBDC();
			BigDecimal pKey=new BigDecimal(dcParam.getpMap().get(WorkFlowTemplate.PKEY).toString());
			dbDc = workflowTemplateSerivice.deleteWorkflow(pKey);
			retJson = toJson(dbDc);
		} catch (Exception e)
		{
			dbLogger.logErrorToJson(e, WorkflowtemplateController.class);
		}
		return retJson;
	}   
}
