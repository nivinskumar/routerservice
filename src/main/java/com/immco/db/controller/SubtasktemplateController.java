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
import com.immco.db.model.router.templates.SubTaskTemplate;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.SUBTASKTEMPLATECONTROLLER;
import com.immco.db.service.router.SubTaskTemplateService;

/**
*@formatter:off
*/

@RestController
@RequestMapping("/routerservice/subtasktemplate")
public class SubtasktemplateController extends BaseController
{
	@Autowired
	SubTaskTemplateService subTaskTemplateService;
	
	@Autowired
	DbLogger dbLogger;
	
	@RequestMapping(value = SUBTASKTEMPLATECONTROLLER.CREATE_OR_UPDATE_SUBTASKTEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })   
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		DBDC dbDc=new DBDC();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			SubTaskTemplate subTaskTemplate = parseIPersistable(json, SubTaskTemplate.class);
			switch(dcParam.getCtx()){
			case SubTaskTemplate.CREATE_SUBTASKTEMPLATE:{
				dbDc=subTaskTemplateService.createOrUpdateSubTaskTemplate(subTaskTemplate);
				if(dbDc.isSuccess())
					WorkflowCacheBuilder.getInstance().updateSubTaskCache(subTaskTemplate);
				retJson = toJson(dbDc);
				break;
			}
			default:break;
			
			}
		} catch (Exception e)
		{
			dbLogger.logErrorToJson(e, SubtasktemplateController.class);
		}
		return retJson;
	} 

	@RequestMapping(value = SUBTASKTEMPLATECONTROLLER.FIND_SUBTASKTEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })    
	@ResponseBody
	public String findSubtasktemplate(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			SubTaskTemplate subTaskTemplate = subTaskTemplateService.findSubTasktemplate(dcParam);
			retJson= toJson(subTaskTemplate);
			
		}
		catch(Exception e)
		{
			dbLogger.logErrorToJson(e, SubtasktemplateController.class);
		}
		return retJson;
	}

	@RequestMapping(value = SUBTASKTEMPLATECONTROLLER.DELETE_SUBTASKTEMPLATE, method = RequestMethod.POST, produces =    { "application/hal+json" })  
	@ResponseBody
	public String deleteSubtasktemplate(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			BigDecimal pKey=new BigDecimal(dcParam.getpMap().get(SubTaskTemplate.PKEY).toString());
			DBDC dbDc=subTaskTemplateService.deleteSubTaskTemplate(dcParam);
			retJson = toJson(dbDc);
			if(dbDc.isSuccess())
				WorkflowCacheBuilder.getInstance().removeSubTask(pKey);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return retJson;
	}   
}

