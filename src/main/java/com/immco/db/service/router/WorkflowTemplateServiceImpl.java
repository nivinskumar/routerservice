package com.immco.db.service.router;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.dao.router.WorkflowTemplateDao;
import com.immco.db.model.router.exec.WorkFlowExec;
import com.immco.db.model.router.templates.WorkFlowTemplate;
import com.immco.db.proxy.DCParam;

@Service("workflowTemplateSerivice")
@Transactional
public class WorkflowTemplateServiceImpl implements WorkflowTemplateService {

	@Autowired
	WorkflowTemplateDao workflowTemplateDao;

	@Autowired
	DbLogger dbLogger;

	@Override
	public DBDC createWorkflowTemplate(WorkFlowTemplate workFlowTemplate) {
		DBDC dbdc = new DBDC();
		try {
			WorkFlowTemplate templateByConsType = workflowTemplateDao.checkWorflowWithConstructionType(
					workFlowTemplate.getConstructionTypePkey(), workFlowTemplate.getpKey());
			if (templateByConsType != null) {
				dbdc.setErrorMsg("Workflow Alreay Created For This Construction Type");
			} else {
				WorkFlowTemplate templateByID = workflowTemplateDao
						.findWorkflowTemplateByID(workFlowTemplate.getWorkflowId(), workFlowTemplate.getpKey());
				WorkFlowTemplate templateByName = workflowTemplateDao
						.findWorkflowTemplateByName(workFlowTemplate.getWorkflowName(), workFlowTemplate.getpKey());

				dbdc.setErrorMsg("Workflow Alreay Created For This Construction Type");
				if (templateByID != null) {
					dbdc.setErrorMsg("Workflow ID Alreay Exists");
				} else if (templateByName != null) {
					dbdc.setErrorMsg("Workflow Name Alreay Exists");
				} else {
					workflowTemplateDao.createWorkflowTemplate(workFlowTemplate);
					dbdc.setSuccessMsg("Workflow Template " + workFlowTemplate.getWorkflowName() + " Created");
				}
			}
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, WorkflowTemplateServiceImpl.class);
		}
		return dbdc;
	}

	@Override
	public WorkFlowTemplate findWorkflowTemplate(DCParam dcParam) {
		HashMap<String, Object> map = dcParam.getpMap();
		BigDecimal pKey = new BigDecimal(map.get(WorkFlowTemplate.PKEY).toString());
		WorkFlowTemplate workFlowTemplate = workflowTemplateDao.findWorkflowTemplateByPK(pKey);
		return workFlowTemplate;
	}

	@Override
	public DBDC updateWorkflowTemplate(WorkFlowTemplate workFlowTemplate) {
		DBDC dbdc = new DBDC();
		try {
			WorkFlowTemplate templateByConsType = workflowTemplateDao.checkWorflowWithConstructionType(
					workFlowTemplate.getConstructionTypePkey(), workFlowTemplate.getpKey());

			if (templateByConsType != null) {
				dbdc.setErrorMsg("Workflow Alreay Created For This Construction Type");
			} else {
				WorkFlowTemplate templateByID = workflowTemplateDao
						.findWorkflowTemplateByID(workFlowTemplate.getWorkflowId(), workFlowTemplate.getpKey());
				WorkFlowTemplate templateByName = workflowTemplateDao
						.findWorkflowTemplateByName(workFlowTemplate.getWorkflowName(), workFlowTemplate.getpKey());

				if (templateByID != null) {
					dbdc.setErrorMsg("Workflow ID Alreay Exists");
				} else if (templateByName != null) {
					dbdc.setErrorMsg("Workflow Name Alreay Exists");
				} else {
					workflowTemplateDao.updateWorkflowTemplate(workFlowTemplate);
					dbdc.setSuccessMsg("Workflow Template " + workFlowTemplate.getWorkflowName() + " Updated");
				}
			}
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, WorkflowTemplateServiceImpl.class);
		}

		return dbdc;
	}

	@Override
	public List<WorkFlowTemplate> getAllWorkflowTemplates() {
		return workflowTemplateDao.getAllWorkflowTemplates();
	}

	@Override
	public DBDC deleteWorkflow(BigDecimal pKey) throws Exception {
		DBDC dbdc = new DBDC();
		try {
			int count = workflowTemplateDao.checkPhaseExists(pKey);
			int workflowExec = workflowTemplateDao.findWorkflowExec(pKey);
			if (count > 0) {
				dbdc.setErrorMsg("Phase Exists for this Workflow");
			} else if (workflowExec > 0) {
				dbdc.setErrorMsg("Workflow is Associated to Job");
			} else {
				workflowTemplateDao.deleteWorkflow(pKey);
				dbdc.setSuccessMsg("Workflow Template Deleted");
			}
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, WorkflowTemplateServiceImpl.class);
		}

		return dbdc;
	}

}
