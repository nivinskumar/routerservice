package com.immco.db.service.router;

import java.math.BigDecimal;
import java.util.List;

import com.immco.common.DBDC;
import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.templates.UDefFormTemplate;

public interface UdefFormTemplateService {

	DBDC createOrUpdateTemplate(UDefFormTemplate parseIPersistable);
	
	UDefFormTemplate  findUdefFormTemplateByPkey(BigDecimal pKey);
	
	DBDC deleteUdefFormTemplate(BigDecimal pKey);
	
	DBDC removeUdefFormTaskAssociation(BigDecimal pKey);
	
	List<UDefFormTemplate> getAllUdefFormTemplates();
	
	BigDecimal findTaskByUdefFormPkey(BigDecimal pKey);
	
	TaskExec findFirstTaskExec(BigDecimal cwsId);

	List<TaskExec> findNextTaskExec(BigDecimal cwsId, String task_id);

	List<TaskExec> findPreviousTaskExec(BigDecimal cwsId, String task_id);

	List<TaskExec> findCurrentTaskExec(BigDecimal cwsId);

	List<TaskExec> findLastTaskExec(BigDecimal cwsId);
}
