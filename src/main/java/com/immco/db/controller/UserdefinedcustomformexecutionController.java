package com.immco.db.controller;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.model.router.exec.UDefCustomFormExec;
import com.immco.db.model.router.exec.UDefTaskExec;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.USERDEFINEDCUSTOMFORMEXECUTIONCONTROLLER;
import com.immco.db.service.exec.UdefCustomFormExecService;

/**
*@formatter:off
*/

@RestController
@RequestMapping("/routerservice/userdefinedcustomformexecution")
public class UserdefinedcustomformexecutionController extends BaseController
{
	@Autowired UdefCustomFormExecService udefCustomFormExecSerive;
	@Autowired DbLogger dbLogger;
	
	@RequestMapping(value = USERDEFINEDCUSTOMFORMEXECUTIONCONTROLLER.CREATE_OR_UPDATE_USERDEFINEDCUSTOMFORMEXECUTION, method = RequestMethod.POST, produces =    { "application/hal+json" })   
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		DBDC dbdc=new DBDC();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			UDefTaskExec taskExec = parseIPersistable(json, UDefTaskExec.class);
			dbdc = udefCustomFormExecSerive.createOrUpdateCustomForm(taskExec);
			retJson = toJson(dbdc);
		} catch (Exception e)
		{
			dbLogger.logErrorToJson(e, UserdefinedcustomformexecutionController.class);
		}
		return retJson;
	} 

	@RequestMapping(value = USERDEFINEDCUSTOMFORMEXECUTIONCONTROLLER.FIND_USERDEFINEDCUSTOMFORMEXECUTION, method = RequestMethod.POST, produces =    { "application/hal+json" })    
	@ResponseBody
	public String findUserdefinedcustomformexecution(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
			DCParam dcParam = fromJSON(json, DCParam.class);
			UDefTaskExec udefTaskExec = udefCustomFormExecSerive.findUdefCustomFormExec(dcParam);
			retJson= toJson(udefTaskExec);
		}
		catch(Exception e)
		{
			dbLogger.logErrorToJson(e, UserdefinedcustomformexecutionController.class);
		}
		return retJson;
	}

	@RequestMapping(value = USERDEFINEDCUSTOMFORMEXECUTIONCONTROLLER.DELETE_USERDEFINEDCUSTOMFORMEXECUTION, method = RequestMethod.POST, produces =    { "application/hal+json" })  
	@ResponseBody
	public String deleteUserdefinedcustomformexecution(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
			//  DCParam dcParam = fromJSON(json, DCParam.class);
			DBDC dbDc=null;
			retJson = toJson(dbDc);
		} catch (Exception e)
		{
			dbLogger.logErrorToJson(e, UserdefinedcustomformexecutionController.class);
		}
		return retJson;
	}   
}
