package com.immco.db.service.router;

import java.util.List;

import com.immco.common.DBDC;
import com.immco.db.model.router.templates.SubTaskTemplate;
import com.immco.db.proxy.DCParam;

public interface SubTaskTemplateService {
	DBDC createOrUpdateSubTaskTemplate(SubTaskTemplate subTaskTemplate) throws Exception;

	DBDC updateSubTaskTemplate(SubTaskTemplate subTaskTemplate) throws Exception;

	SubTaskTemplate findSubTasktemplate(DCParam dcParam);
	
	List<SubTaskTemplate> findAllSubTaskTemplates();
	
	DBDC deleteSubTaskTemplate(DCParam dcParam) throws Exception;

}
