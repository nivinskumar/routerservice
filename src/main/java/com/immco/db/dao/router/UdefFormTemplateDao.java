package com.immco.db.dao.router;

import java.math.BigDecimal;
import java.util.List;

import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.templates.UDefFormTemplate;

public interface UdefFormTemplateDao {

	public void createOrUpdateTemplate(UDefFormTemplate parseIPersistable) throws Exception;
	
	public UDefFormTemplate  findUdefFormTemplateByPkey(BigDecimal pKey);
	
	public void deleteUdefFormTemplate(BigDecimal pKey) throws Exception; 
	
	public int findAssociatedToTask(BigDecimal pKey);
	
	public BigDecimal removeUdefFormTaskAssociation(BigDecimal pKey) throws Exception;
	
	public List<UDefFormTemplate> getAllUdefFormTemplates();
	
	public BigDecimal findTaskByUdefFormPkey(BigDecimal pKey);
	
	public TaskExec findFirstTaskExec(BigDecimal cwsId);

	List<TaskExec> findNextTaskExec(BigDecimal cwsId, String task_id);

	List<TaskExec> findPreviousTaskExec(BigDecimal cwsId, String task_id);

	public List<TaskExec> findCurrentTaskExec(BigDecimal cwsId);

	public List<TaskExec> findLastTaskExec(BigDecimal cwsId);
}
