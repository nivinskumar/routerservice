package com.immco.db.service.router;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.dao.router.UdefFormTemplateDao;
import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.templates.UDefFormTemplate;

@Service("udefFormTemplateService")
@Transactional
public class UdefFormTemplateServiceImpl implements UdefFormTemplateService {

	@Autowired
	private UdefFormTemplateDao udefFormTemplateDao;
	
	@Autowired
	private DbLogger dbLogger;
	
	@Override
	public DBDC createOrUpdateTemplate(UDefFormTemplate parseIPersistable) {
		DBDC dbdc = new DBDC();
		BigDecimal pKey = parseIPersistable.getpKey();
		try {
			udefFormTemplateDao.createOrUpdateTemplate(parseIPersistable);
			if(pKey==null){
				dbdc.setSuccessMsg("Form Template "+parseIPersistable.getTemplateName()+" Created");
				}
				else
				{
					dbdc.setSuccessMsg("Form Template "+parseIPersistable.getTemplateName()+" Updated");	
				}
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, UdefFormTemplateServiceImpl.class);
		}
		return dbdc;
	}

	@Override
	public UDefFormTemplate findUdefFormTemplateByPkey(BigDecimal pKey) {
		UDefFormTemplate findUdefFormTemplate = udefFormTemplateDao.findUdefFormTemplateByPkey(pKey);
		return findUdefFormTemplate;
	}

	@Override
	public DBDC deleteUdefFormTemplate(BigDecimal pKey) {
		DBDC dbdc=new DBDC();
		
			try {
				int count = udefFormTemplateDao.findAssociatedToTask(pKey);
				if(count>0){
					dbdc.setErrorMsg("The Template is Associated to "+count+" Task(s)");
				}else{
				udefFormTemplateDao.deleteUdefFormTemplate(pKey);
				dbdc.setSuccessMsg("Template Deleted");
				}
			} catch (Exception e) {
				dbLogger.logErrorToJson(e, UdefFormTemplateServiceImpl.class);
		}
		return dbdc;
	}

	@Override
	public DBDC removeUdefFormTaskAssociation(BigDecimal pKey) {
		DBDC dbdc=new DBDC();
		try {
			BigDecimal udefFormPkey = udefFormTemplateDao.removeUdefFormTaskAssociation(pKey);
			dbdc.setInfoMsg(udefFormPkey.toString());
			dbdc.setSuccess(true);
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, UdefFormTemplateServiceImpl.class);
		}
		return dbdc;
	}

	@Override
	public List<UDefFormTemplate> getAllUdefFormTemplates() {
		return udefFormTemplateDao.getAllUdefFormTemplates();
	}

	@Override
	public BigDecimal findTaskByUdefFormPkey(BigDecimal pKey) {
		return null;
	}

	@Override
	public TaskExec findFirstTaskExec(BigDecimal cwsId) {
		TaskExec taskExec = udefFormTemplateDao.findFirstTaskExec(cwsId);
		return taskExec;
	}
	
	@Override
	public List<TaskExec> findNextTaskExec(BigDecimal cwsId, String task_id) {
		List<TaskExec> taskExec = udefFormTemplateDao.findNextTaskExec(cwsId,task_id);
		return taskExec;
	}
	
	@Override
	public List<TaskExec> findCurrentTaskExec(BigDecimal cwsId) {
		List<TaskExec> taskExec = udefFormTemplateDao.findCurrentTaskExec(cwsId);
		return taskExec;
	}
	
	@Override
	public List<TaskExec> findPreviousTaskExec(BigDecimal cwsId, String task_id) {
		List<TaskExec> taskExec = udefFormTemplateDao.findPreviousTaskExec(cwsId,task_id);
		return taskExec;
	}
	
	@Override
	public List<TaskExec> findLastTaskExec(BigDecimal cwsId) {
		List<TaskExec>  taskExec = udefFormTemplateDao.findLastTaskExec(cwsId);
		return taskExec;
	}
}
