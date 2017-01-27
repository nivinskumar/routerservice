package com.immco.db.dao.router;

import java.math.BigDecimal;
import java.util.List;

import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.UDefFormTemplate;

public interface TaskTemplateDao {
	public void createTaskTemplate(TaskTemplate taskTemplate) throws Exception;

	public void updateTaskTemplate(TaskTemplate taskTemplate) throws Exception;

	TaskTemplate findTaskTemplateByPK(BigDecimal PK);

	TaskTemplate findTaskTemplateByID(String id, BigDecimal pkey);

	int findTaskTemplateByName(String name, BigDecimal pkey,BigDecimal phasePkey);
	
	List<TaskTemplate> findAllTaskTemplates();
	
	public void associateUdefForm(BigDecimal taskPkey,BigDecimal UdefPkey) throws Exception;

	public UDefFormTemplate  findUdefFormTemplate(BigDecimal taskTemplatePkey);

	public void createOrUpdateTemplate(UDefFormTemplate parseIPersistable);
	
	public void deleteTaskTemplate(BigDecimal pKey) throws Exception;
	
	public UDefFormTemplate  findUdefFormTemplateByPkey(BigDecimal pKey);
	
	public int checkUdefFromAssociation(BigDecimal taskPkey);
	
	public int checkMilestoneAssociation(BigDecimal taskPkey);
}
