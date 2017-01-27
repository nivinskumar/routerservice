package com.immco.db.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.immco.db.DbLogger;
import com.immco.common.DBDC;
import com.immco.db.model.router.role.RoleTemplate;
import com.immco.db.model.router.role.RoleTemplateAppFunMap;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.ROLETEMPLATECONTROLLER;
import com.immco.db.service.TemplateService;

/**
 * @formatter:off
 */

@RestController
@RequestMapping("/routerservice/roletemplatecontroller")
public class RoleTemplateController extends BaseController {
	@Autowired
	private TemplateService templateService;

	@Autowired
	private DbLogger dbLogger;

	@RequestMapping(value = ROLETEMPLATECONTROLLER.CREATE_OR_UPDATE_ROLETEMPLATECONTROLLER, method = RequestMethod.POST, produces = { "application/hal+json" })
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			String ctx = dcParam.getCtx();
			HashMap<Class, Object> iPersistableMap = dcParam.getIPersistableMap();
			Object object = iPersistableMap.get(RoleTemplateAppFunMap.class);
			json = toJson(object);
			List parseIPersistable = getAllObjects(json, RoleTemplateAppFunMap.class);

			object = iPersistableMap.get(RoleTemplate.class);
			json = toJson(object);
			RoleTemplate roleTemplate = (RoleTemplate) fromJSON(json, RoleTemplate.class);
			BigDecimal getpKey = roleTemplate.getpKey();
			roleTemplate.setRoleTemplateName(roleTemplate.getRoleTemplateName().toUpperCase().trim());

			RoleTemplate findRoleTemplateByName = templateService.findRoleTemplateByName(roleTemplate.getRoleTemplateName());
			if (findRoleTemplateByName != null) {
				if (roleTemplate.getpKey() == null) {
					DBDC dbDc = new DBDC();
					dbDc.setSuccess(false);
					dbDc.setErrorMsg("Role Template " + roleTemplate.getRoleTemplateName() + " already exists");
					retJson = toJson(dbDc);
					return retJson;
				} else if (roleTemplate.getpKey() != null && roleTemplate.getpKey().longValue() != findRoleTemplateByName.getpKey().longValue()) {
					DBDC dbDc = new DBDC();
					dbDc.setSuccess(false);
					dbDc.setErrorMsg("Role Template " + roleTemplate.getRoleTemplateName() + " already exists");
					retJson = toJson(dbDc);
					return retJson;
				}
			}

			DBDC dbDc = templateService.createOrUpdateTemplate(roleTemplate, parseIPersistable);
			String msg = "Role Template " + roleTemplate.getRoleTemplateName();
			if (dbDc.isSuccess())
				msg = getpKey != null ? msg + " updated" : msg + " saved";

			dbDc.setSuccessMsg(msg);
			retJson = toJson(dbDc);
		} catch (Exception e) {
			retJson = dbLogger.logErrorToJson(e, RoleTemplateController.class);
		}
		return retJson;
	}

	@RequestMapping(value = ROLETEMPLATECONTROLLER.FIND_ROLETEMPLATECONTROLLER, method = RequestMethod.POST, produces = { "application/hal+json" })
	@ResponseBody
	public String findRoletemplatecontroller(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			String ctx = dcParam.getCtx();
			Object obj = null;
			switch (ctx) {
			case RoleTemplate.CTX_FIND_ROLETEMPLATE_BY_PK:
				BigDecimal pkey = new BigDecimal(dcParam.getpMap().get(RoleTemplate.PKEY).toString());
				obj = templateService.findRoleTemplateById(pkey);
				break;
			case RoleTemplate.CTX_FIND_ALL_ROLETEMPLATE_BY_ROLE:
				BigDecimal rolePkey = new BigDecimal(dcParam.getpMap().get(RoleTemplate.ROLE_PKEY).toString());
				obj = templateService.findRoleTemplateByRole(rolePkey);
				break;
			case RoleTemplate.CTX_FIND_ROLETEMPLATE_BY_NAME:
				String tname = (String) dcParam.getpMap().get(RoleTemplate.ROLE_TEMPLATE_NAME);
				obj = templateService.findRoleTemplateByName(tname);
				break;
			case RoleTemplate.CTX_FIND_ROLETEMPLATE_BY_NAME_AND_ROLE:
				String name = (String) dcParam.getpMap().get(RoleTemplate.ROLE_TEMPLATE_NAME);
				BigDecimal rolePkeys = new BigDecimal(dcParam.getpMap().get(RoleTemplate.ROLE_PKEY).toString());
				obj = templateService.findRoleTemplateByRoleAndName(name, rolePkeys);
				break;
			case RoleTemplate.CTX_FIND_ROLETEMPLATE_BY_PRIMARYROLE:
				BigDecimal primaryRolePkey = new BigDecimal(dcParam.getpMap().get(RoleTemplate.ROLE_PKEY).toString());
				obj = templateService.findRoleTemplateByPrimaryRole(primaryRolePkey);
				break;
			case RoleTemplate.CTX_FIND_ALL_ROLETEMPLATE_BY_PRIMARYROLE:
				obj = templateService.findPrimaryRoleTemplate();
				break;
			}
			retJson = toJson(obj);
		} catch (Exception e) {
			retJson = dbLogger.logErrorToJson(e, RoleTemplateController.class);
		}
		return retJson;
	}

	@RequestMapping(value = ROLETEMPLATECONTROLLER.DELETE_ROLETEMPLATECONTROLLER, method = RequestMethod.POST, produces = { "application/hal+json" })
	@ResponseBody
	public String deleteRoletemplatecontroller(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			String ctx = dcParam.getCtx();
			Number roleTemplatePkey = (Number) dcParam.getpMap().get(RoleTemplate.PKEY);
			DBDC dbDc = templateService.deleteRoleTemplate(roleTemplatePkey);
			retJson = toJson(dbDc);
		} catch (Exception e) {
			retJson = dbLogger.logErrorToJson(e, RoleTemplateController.class);
		}
		return retJson;
	}

}
