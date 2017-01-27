package com.immco.db.dao.router;

import java.math.BigDecimal;
import java.util.List;

import com.immco.db.model.router.templates.SubTaskTemplate;

public interface SubTaskTemplateDao {
	public void createSubTaskTemplate(SubTaskTemplate subTaskTemplate) throws Exception;
	
	public void updateSubTaskTemplate(SubTaskTemplate subTaskTemplate) throws Exception;
	
	SubTaskTemplate findSubTaskTemplateByPK(BigDecimal PK);
	
	SubTaskTemplate findSubTaskTemplateByID(String id,BigDecimal PK);
	
	SubTaskTemplate findSubTaskTemplateByName(String name,BigDecimal PK);

	List<SubTaskTemplate> findAllSubTaskTemplates();
	
	public void deleteSubTasktemplate(BigDecimal pKey) throws Exception;
	
}
