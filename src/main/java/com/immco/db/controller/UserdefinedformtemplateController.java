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
import com.immco.db.model.router.templates.UDefFormTemplate;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.USERDEFINEDFORMTEMPLATECONTROLLER;
import com.immco.db.service.router.TaskTemplateService;
import com.immco.db.service.router.UdefFormTemplateService;

/**
 * @formatter:off
 */

@RestController
@RequestMapping("/routerservice/userdefinedformtemplate")
public class UserdefinedformtemplateController extends BaseController {

	@Autowired
	DbLogger dbLogger;

	@Autowired
	UdefFormTemplateService udefFormTemplateService;

	@RequestMapping(value = USERDEFINEDFORMTEMPLATECONTROLLER.CREATE_OR_UPDATE_USERDEFINEDFORMTEMPLATE, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			String ctx = dcParam.getCtx();
			UDefFormTemplate formTemplate = parseIPersistable(json, UDefFormTemplate.class);
			DBDC dbDc = udefFormTemplateService.createOrUpdateTemplate(formTemplate);
			WorkflowCacheBuilder.instance.updateUdefFormCache(formTemplate);
			retJson = toJson(dbDc);
		} catch (Exception e) {
			retJson=dbLogger.logErrorToJson(e, UserdefinedformtemplateController.class);
		}
		return retJson;
	}

	@RequestMapping(value = USERDEFINEDFORMTEMPLATECONTROLLER.FIND_USERDEFINEDFORMTEMPLATE, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String findUserdefinedformtemplate(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			String ctx = dcParam.getCtx();
			BigDecimal pKey = new BigDecimal(
					dcParam.getpMap().get(UDefFormTemplate.PKEY).toString());
			UDefFormTemplate udefFormTemplate = udefFormTemplateService.findUdefFormTemplateByPkey(pKey);
			retJson = toJson(udefFormTemplate);
		} catch (Exception e) {
			retJson=dbLogger.logErrorToJson(e, UserdefinedformtemplateController.class);
		}
		return retJson;
	}

	@RequestMapping(value = USERDEFINEDFORMTEMPLATECONTROLLER.DELETE_USERDEFINEDFORMTEMPLATE, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String deleteUserdefinedformtemplate(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			DBDC dbDc = new DBDC();
			switch(dcParam.getCtx()){
			case UDefFormTemplate.DELETE_FORM_TEMPLATE:{
				BigDecimal pKey=new BigDecimal(dcParam.getpMap().get(UDefFormTemplate.PKEY).toString());
				dbDc=udefFormTemplateService.deleteUdefFormTemplate(pKey);
				retJson = toJson(dbDc);
				break;
			}
			case UDefFormTemplate.REMOVE_UDEFFORM_FROM_TASK:{
				BigDecimal pKey=new BigDecimal(dcParam.getpMap().get(UDefFormTemplate.TASK_UDEFFORM_MAP_PKEY).toString());
				dbDc=udefFormTemplateService.removeUdefFormTaskAssociation(pKey);
				WorkflowCacheBuilder.instance.removeUdefFormtemplate(new BigDecimal(dbDc.getInfoMsg()));
				retJson=toJson(dbDc);
				break;
			}
			}
			
		} catch (Exception e) {
			retJson=dbLogger.logErrorToJson(e, UserdefinedformtemplateController.class);
		}
		return retJson;
	}
}
