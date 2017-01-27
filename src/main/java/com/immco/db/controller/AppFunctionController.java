package com.immco.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.APPFUNCTIONCONTROLLER;
import com.immco.db.service.TemplateService;

/**
 * @formatter:off
 */

@RestController
@RequestMapping("/routerservice/appfunctioncontroller")
public class AppFunctionController extends BaseController {
	@Autowired
	TemplateService templateService;
	
	@Autowired
	private DbLogger dbLogger;

	@RequestMapping(value = APPFUNCTIONCONTROLLER.CREATE_OR_UPDATE_APPFUNCTIONCONTROLLER, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			
			
			retJson = toJson(new DBDC());
		} catch (Exception e) {
			retJson = dbLogger.logErrorToJson(e, AppFunctionController.class);
		}
		return retJson;
	}

	@RequestMapping(value = APPFUNCTIONCONTROLLER.FIND_APPFUNCTIONCONTROLLER, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String findAppfunctioncontroller(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			// DCParam dcParam = fromJSON(json, DCParam.class);
			retJson = toJson("HUB OBJECT");
		} catch (Exception e) {
			retJson = dbLogger.logErrorToJson(e, AppFunctionController.class);
		}
		return retJson;
	}

	@RequestMapping(value = APPFUNCTIONCONTROLLER.DELETE_APPFUNCTIONCONTROLLER, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String deleteAppfunctioncontroller(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			// DCParam dcParam = fromJSON(json, DCParam.class);
			DBDC dbDc = null;
			retJson = toJson(dbDc);
		} catch (Exception e) {
			retJson = dbLogger.logErrorToJson(e, AppFunctionController.class);
		}
		return retJson;
	}
}
