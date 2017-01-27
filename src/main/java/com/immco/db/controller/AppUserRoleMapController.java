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

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.model.router.role.AppUserRoleMap;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.APPUSERROLEMAPCONTROLLER;
import com.immco.db.service.AppUserRoleMapService;

/**
 * @formatter:off
 */

@RestController
@RequestMapping("/routerservice/appuserrolemapcontroller")
public class AppUserRoleMapController extends BaseController {

	@Autowired
	private AppUserRoleMapService appUserRoleMapService;
	
	@Autowired
	private DbLogger dbLogger;

	@RequestMapping(value = APPUSERROLEMAPCONTROLLER.CREATE_OR_UPDATE_APPUSERROLEMAPCONTROLLER, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			BigDecimal userPkey = new BigDecimal(dcParam.getpMap().get(AppUserRoleMap.USER_PKEY).toString());
			HashMap<Class, Object> iPersistableMap = dcParam.getIPersistableMap();
			Object object = iPersistableMap.get(AppUserRoleMap.class);
			json = toJson(object);
			List appUserRoleMapList = getAllObjects(json, AppUserRoleMap.class);
			DBDC dbDc = appUserRoleMapService.createOrUpdateOrDeleteAppUserRoleMap(appUserRoleMapList, userPkey);
			retJson = toJson(dbDc);
		} catch (Exception e) {
			 retJson=dbLogger.logErrorToJson(e, AppUserRoleMapController.class);
		}
		return retJson;
	}

	@RequestMapping(value = APPUSERROLEMAPCONTROLLER.FIND_APPUSERROLEMAPCONTROLLER, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String findAppuserrolemapcontroller(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			BigDecimal userPkey = new BigDecimal(dcParam.getpMap().get(AppUserRoleMap.USER_PKEY).toString());
			
			String ctx = dcParam.getCtx();
			switch (ctx) {
			case AppUserRoleMap.CTX_FINDBY_USERPKEY_ROLEPKEY:
				BigDecimal rolePkey = new BigDecimal(dcParam.getpMap().get(AppUserRoleMap.ROLE_PKEY).toString());
				AppUserRoleMap appUserRoleMap =appUserRoleMapService.findAppUserRoleMapByUserAndRole(userPkey,rolePkey);
				retJson = toJson(appUserRoleMap);
				break;

			default:
				List<AppUserRoleMap> findAllAppUserRoleMapByUser = appUserRoleMapService
				.findAllAppUserRoleMapByUser(userPkey);
				retJson = toJson(findAllAppUserRoleMapByUser);
				break;
			}
			
			
		} catch (Exception e) {
			 retJson=dbLogger.logErrorToJson(e, AppUserRoleMapController.class);
		}
		return retJson;
	}

	@RequestMapping(value = APPUSERROLEMAPCONTROLLER.DELETE_APPUSERROLEMAPCONTROLLER, method = RequestMethod.POST, produces = {
			"application/hal+json" })
	@ResponseBody
	public String deleteAppuserrolemapcontroller(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			// DCParam dcParam = fromJSON(json, DCParam.class);
			DBDC dbDc = null;
			retJson = toJson(dbDc);
		} catch (Exception e) {
			 retJson=dbLogger.logErrorToJson(e, AppUserRoleMapController.class);
		}
		return retJson;
	}
}
