package com.immco.db.service.router;

import java.math.BigDecimal;
import java.util.List;

import com.immco.common.DBDC;
import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.UDefFormTemplate;
import com.immco.db.proxy.DCParam;

public interface TaskTemplateService {

	DBDC createOrUpdateTaskTemplate(TaskTemplate taskTemplate,DCParam dcParam) throws Exception;

	TaskTemplate findTaskTemplate(DCParam dcParam);
	
	List<TaskTemplate> findAllTaskTemplates();
	
	DBDC associateUdefFormTemplate(DCParam dcParam) throws Exception;
	
	UDefFormTemplate findUdefFormTemplate(BigDecimal taskTemplatePkey);

	//DBDC createOrUpdateTemplate(UDefFormTemplate parseIPersistable);
	
	DBDC deleteTaskTemplate(DCParam dcParam);
	
	////UDefFormTemplate  findUdefFormTemplateByPkey(BigDecimal pKey);
}
