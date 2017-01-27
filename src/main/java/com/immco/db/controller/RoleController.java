package com.immco.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.immco.common.DBDC;
import com.immco.db.model.router.role.Role;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.ROLECONTROLLER;
import com.immco.db.service.RoleService;

/**
 * @formatter:off
 */
@RestController
@RequestMapping("/dbservice/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = ROLECONTROLLER.CREATE_OR_UPDATE_ROLE, method = RequestMethod.POST, produces = {"application/hal+json" })
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			String ctx = dcParam.getCtx();
			Role role = parseIPersistable(json, Role.class);
			DBDC dbDc = roleService.createRole(role);
			retJson = toJson(dbDc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retJson;
	}

	@RequestMapping(value = ROLECONTROLLER.FIND_ROLE, method = RequestMethod.POST, produces = {"application/hal+json" })
	@ResponseBody
	public String findRole(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			// DCParam dcParam = fromJSON(json, DCParam.class);
			retJson = toJson("HUB OBJECT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retJson;
	}

	@RequestMapping(value = ROLECONTROLLER.DELETE_ROLE, method = RequestMethod.POST, produces = {"application/hal+json" })
	@ResponseBody
	public String deleteRole(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			// DCParam dcParam = fromJSON(json, DCParam.class);
			DBDC dbDc = null;
			retJson = toJson(dbDc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retJson;
	}
}