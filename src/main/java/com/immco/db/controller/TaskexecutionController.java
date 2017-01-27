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

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.role.AppUserRoleMap;
import com.immco.db.proxy.DCParam;
import com.immco.db.remote.RouterEndPoints.TASKEXECUTIONCONTROLLER;
import com.immco.db.service.router.UdefFormTemplateService;

/**
 * @formatter:off
 */

@RestController
@RequestMapping("/routerservice/taskexecution")
public class TaskexecutionController extends BaseController {

	@Autowired
	private DbLogger dbLogger;

	@Autowired
	private UdefFormTemplateService udefFormTemplateService;

	@RequestMapping(value = TASKEXECUTIONCONTROLLER.CREATE_OR_UPDATE_TASKEXECUTION, method = RequestMethod.POST, produces = { "application/hal+json" })
	@ResponseBody
	public String create(@PathVariable String auditableUserId, @RequestBody String json) {
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

	@RequestMapping(value = TASKEXECUTIONCONTROLLER.FIND_TASKEXECUTION, method = RequestMethod.POST, produces = { "application/hal+json" })
	@ResponseBody
	public String findTaskexecution(@PathVariable String auditableUserId, @RequestBody String json) {
		String retJson = defaultErrMsg();
		try {
			DCParam dcParam = fromJSON(json, DCParam.class);
			BigDecimal cwsId = new BigDecimal(dcParam.getpMap().get(TaskExec.CWS_ID).toString());

			String ctx = dcParam.getCtx();
			switch (ctx) {
			case TaskExec.CTX_FIND_FIRST:
				TaskExec findFirstTaskExec = udefFormTemplateService.findFirstTaskExec(cwsId);
				retJson = toJson(findFirstTaskExec);
				break;
			case TaskExec.CTX_FIND_LAST:
				List<TaskExec> findLastTaskExec = udefFormTemplateService.findLastTaskExec(cwsId);
				retJson = toJson(findLastTaskExec);
				break;

			case TaskExec.CTX_FIND_NEXT:
				String nextTaskId = dcParam.getpMap().get(TaskExec.TASK_ID).toString();
				List<TaskExec> findNextTaskExec = udefFormTemplateService.findNextTaskExec(cwsId, nextTaskId);
				retJson = toJson(findNextTaskExec);
				break;

			case TaskExec.CTX_FIND_PREVIOUS:
				String previousTaskId = dcParam.getpMap().get(TaskExec.TASK_ID).toString();
				List<TaskExec> findPreviousTaskExec = udefFormTemplateService.findPreviousTaskExec(cwsId, previousTaskId);
				retJson = toJson(findPreviousTaskExec);
				break;

			case TaskExec.CTX_FIND_CURRENT:
				List<TaskExec> findCurrentTaskExec = udefFormTemplateService.findCurrentTaskExec(cwsId);
				retJson = toJson(findCurrentTaskExec);
				break;
			}

		} catch (Exception e) {
			retJson = dbLogger.logErrorToJson(e, AppUserRoleMapController.class);
		}
		return retJson;
	}

	@RequestMapping(value = TASKEXECUTIONCONTROLLER.DELETE_TASKEXECUTION, method = RequestMethod.POST, produces = { "application/hal+json" })
	@ResponseBody
	public String deleteTaskexecution(@PathVariable String auditableUserId, @RequestBody String json) {
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