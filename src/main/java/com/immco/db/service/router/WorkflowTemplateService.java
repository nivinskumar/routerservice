package com.immco.db.service.router;

import java.math.BigDecimal;
import java.util.List;

import com.immco.common.DBDC;
import com.immco.db.model.router.templates.WorkFlowTemplate;
import com.immco.db.proxy.DCParam;

public interface WorkflowTemplateService {
	
    DBDC createWorkflowTemplate(WorkFlowTemplate workFlowTemplate) throws Exception;
    
    DBDC updateWorkflowTemplate(WorkFlowTemplate workFlowTemplate) throws Exception;

    WorkFlowTemplate findWorkflowTemplate(DCParam dcParam);
    
    List<WorkFlowTemplate> getAllWorkflowTemplates();
    
    DBDC deleteWorkflow(BigDecimal pKey) throws Exception;

}
