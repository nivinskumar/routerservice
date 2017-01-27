package com.immco.db.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.immco.common.DBDC;
import com.immco.db.controller.ControllerConstants.HUMANTASKCONTROLLER;

/**
*@formatter:off
*/

@RestController
@RequestMapping("/router/humantask")
public class HumantaskController extends BaseController
{
	@RequestMapping(value = HUMANTASKCONTROLLER.CREATE_OR_UPDATE_HUMANTASK, method = RequestMethod.POST, produces =    { "application/hal+json" })   
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
			//  DCParam dcParam = fromJSON(json, DCParam.class);
			DBDC dbDc=null;
			retJson = toJson(dbDc);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return retJson;
	} 

	@RequestMapping(value = HUMANTASKCONTROLLER.FIND_HUMANTASK, method = RequestMethod.POST, produces =    { "application/hal+json" })    
	@ResponseBody
	public String findHumantask(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
			//  DCParam dcParam = fromJSON(json, DCParam.class);
			retJson= toJson("HUB OBJECT");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return retJson;
	}

	@RequestMapping(value = HUMANTASKCONTROLLER.DELETE_HUMANTASK, method = RequestMethod.POST, produces =    { "application/hal+json" })  
	@ResponseBody
	public String deleteHumantask(@PathVariable String auditableUserId, @RequestBody String json)
	{
		String retJson = defaultErrMsg();
		try
		{
			//  DCParam dcParam = fromJSON(json, DCParam.class);
			DBDC dbDc=null;
			retJson = toJson(dbDc);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return retJson;
	}   
}