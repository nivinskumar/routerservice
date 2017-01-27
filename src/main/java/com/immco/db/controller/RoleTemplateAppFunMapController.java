package com.immco.db.controller;

import java.math.BigDecimal;
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
import com.immco.db.model.router.role.RoleTemplateAppFunMap;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.ROLETEMPLATEAPPFUNMAPCONTROLLER;
import com.immco.db.service.TemplateService;

@RestController
@RequestMapping("/routerservice/roletemplateappfunmapcontroller")
public class RoleTemplateAppFunMapController extends BaseController {

	@Autowired
	private TemplateService templateService;

	@Autowired
	private DbLogger dbLogger;

	@RequestMapping(value = ROLETEMPLATEAPPFUNMAPCONTROLLER.CREATE_OR_UPDATE_ROLETEMPLATEAPPFUNMAPCONTROLLER, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DBDC dbDc = null;
			retJson = toJson(dbDc);
		} catch (Exception e) {
			retJson = dbLogger.logErrorToJson(e, RoleTemplateAppFunMapController.class);
		}
		return retJson;
	}

	@RequestMapping(value = ROLETEMPLATEAPPFUNMAPCONTROLLER.FIND_ROLETEMPLATEAPPFUNMAPCONTROLLER, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String findRoletemplateappfunmapcontroller(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			BigDecimal pkey = new BigDecimal(
					dcParam.getpMap().get(RoleTemplateAppFunMap.ROLE_TEMPLATE_PKEY).toString());
			List<RoleTemplateAppFunMap> object = templateService.findRoleTemplateAppFunMapByTemplateId(pkey);
			retJson = toJson(object);
		} catch (Exception e) {
			retJson = dbLogger.logErrorToJson(e, RoleTemplateAppFunMapController.class);
		}
		return retJson;
	}

	@RequestMapping(value = ROLETEMPLATEAPPFUNMAPCONTROLLER.DELETE_ROLETEMPLATEAPPFUNMAPCONTROLLER, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String deleteRoletemplateappfunmapcontroller(@PathVariable String auditableUserId,
			@RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			// DCParam dcParam = fromJSON(json, DCParam.class);
			DBDC dbDc = null;
			retJson = toJson(dbDc);
		} catch (Exception e) {
			retJson = dbLogger.logErrorToJson(e, RoleTemplateAppFunMapController.class);
		}
		return retJson;
	}
}
