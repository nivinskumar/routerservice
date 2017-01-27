package com.immco.db.dao.router;

import java.math.BigDecimal;
import java.util.List;

import com.immco.db.model.router.exec.WorkFlowExec;
import com.immco.db.model.router.templates.WorkFlowTemplate;

public interface WorkflowTemplateDao {

	public void createWorkflowTemplate(WorkFlowTemplate workFlowTemplate) throws Exception;
	
	public void updateWorkflowTemplate(WorkFlowTemplate workFlowTemplate) throws Exception;

	WorkFlowTemplate findWorkflowTemplateByPK(BigDecimal PK);

	WorkFlowTemplate findWorkflowTemplateByName(String name,BigDecimal pKey);
	
	WorkFlowTemplate findWorkflowTemplateByID(String id,BigDecimal pKey);
	
	List<WorkFlowTemplate> getAllWorkflowTemplates();
	
	public void deleteWorkflow(BigDecimal pKey) throws Exception; 
	
	public int checkPhaseExists(BigDecimal workflowPkey);
	
	public int findWorkflowExec(BigDecimal workflowTemplatePkey);
	
	WorkFlowTemplate checkWorflowWithConstructionType(BigDecimal consTypePkey,BigDecimal workflowPkey);
}
