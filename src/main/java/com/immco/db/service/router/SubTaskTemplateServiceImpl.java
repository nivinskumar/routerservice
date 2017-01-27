package com.immco.db.service.router;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.dao.router.SubTaskTemplateDao;
import com.immco.db.model.router.templates.SubTaskTemplate;
import com.immco.db.proxy.DCParam;

@Service("subTaskTemplateService")
@Transactional
public class SubTaskTemplateServiceImpl implements SubTaskTemplateService {

	@Autowired
	private SubTaskTemplateDao subTaskTemplateDao;

	@Autowired
	private DbLogger dbLogger;

	@Override
	public DBDC createOrUpdateSubTaskTemplate(SubTaskTemplate subTaskTemplate) {
		DBDC dbdc = new DBDC();
		try {
				SubTaskTemplate templateByID = subTaskTemplateDao
						.findSubTaskTemplateByID(subTaskTemplate.getSubTaskId(), subTaskTemplate.getpKey());
				SubTaskTemplate templateByName = subTaskTemplateDao
						.findSubTaskTemplateByName(subTaskTemplate.getSubTaskNm(), subTaskTemplate.getpKey());
				if (templateByID != null) {
					dbdc.setErrorMsg("Sub Task ID Already Exists");
				} else if (templateByName != null) {
					dbdc.setErrorMsg("Sub Task Name Already Exists");
				} else if(subTaskTemplate.getpKey() == null) {
					subTaskTemplateDao.createSubTaskTemplate(subTaskTemplate);
					dbdc.setSuccessMsg("Sub Task Template "+subTaskTemplate.getSubTaskNm()+" Created");
					dbdc.setInfoMsg(subTaskTemplate.getpKey().toString());
			} else {
				subTaskTemplateDao.updateSubTaskTemplate(subTaskTemplate);
				dbdc.setSuccessMsg("Sub Task Template "+subTaskTemplate.getSubTaskNm()+" Updated");
				dbdc.setInfoMsg(subTaskTemplate.getpKey().toString());
			}
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, SubTaskTemplateServiceImpl.class);
		}
		return dbdc;
	}

	/**
	 * Anoop, use the above api
	 */
	@Override
	@Deprecated
	public DBDC updateSubTaskTemplate(SubTaskTemplate subTaskTemplate) throws Exception {
		DBDC dbdc = new DBDC();
		try {
			SubTaskTemplate templateByID = subTaskTemplateDao.findSubTaskTemplateByID(subTaskTemplate.getSubTaskId(),
					subTaskTemplate.getpKey());
			SubTaskTemplate templateByName = subTaskTemplateDao
					.findSubTaskTemplateByName(subTaskTemplate.getSubTaskId(), subTaskTemplate.getpKey());
			if (templateByID != null) {
				dbdc.setErrorMsg("Sub Task ID Already Exists");
			} else if (templateByName != null) {
				dbdc.setErrorMsg("Sub Task Name Already Exists");
			} else {
				subTaskTemplateDao.updateSubTaskTemplate(subTaskTemplate);
				dbdc.setSuccessMsg("Sub Task updated !!");
			}
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, SubTaskTemplateServiceImpl.class);
		}
		return dbdc;
	}

	@Override
	public SubTaskTemplate findSubTasktemplate(DCParam dcParam) {
		BigDecimal pkey = new BigDecimal(dcParam.getpMap().get(SubTaskTemplate.PKEY).toString());
		SubTaskTemplate subTaskTemplate = subTaskTemplateDao.findSubTaskTemplateByPK(pkey);
		return subTaskTemplate;
	}

	@Override
	public List<SubTaskTemplate> findAllSubTaskTemplates() {
		return subTaskTemplateDao.findAllSubTaskTemplates();
	}

	@Override
	public DBDC deleteSubTaskTemplate(DCParam dcParam) throws Exception {
		DBDC dbdc=new DBDC();
		BigDecimal pKey=new BigDecimal(dcParam.getpMap().get(SubTaskTemplate.PKEY).toString());
		subTaskTemplateDao.deleteSubTasktemplate(pKey);
		dbdc.setSuccessMsg("SubTask Deleted");
		return dbdc;
	}
	
	

}
