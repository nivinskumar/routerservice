package com.immco.db.dao.router;

import java.math.BigDecimal;
import java.util.List;

import com.immco.common.DBDC;
import com.immco.db.model.router.templates.Milestones;
import com.immco.db.model.router.templates.WorkflowPhase;

public interface MilestoneTemplateDao {
	
	public void createMilestoneTemplate(WorkflowPhase milestoneTemplate) throws Exception;
	
	public void updateMilestoneTemplate(WorkflowPhase milestoneTemplate) throws Exception;
	
	WorkflowPhase findMilestoneTemplateByPK(BigDecimal PK);
	
	WorkflowPhase findMilestoneTemplateByID(String Id,BigDecimal pKey );
	
	WorkflowPhase findMilestoneTemplateByName(String name,BigDecimal pKey );
	
	List<WorkflowPhase> getAllMilestones();
	
	public void deleteMilestoneTemplate(Number pkey,Number workflowPkey) throws Exception;
	
	public void associateTaskAndMilestones(BigDecimal milestonePkey,List<Integer> taskPkeyList) throws Exception;
	
	public void createOrUpdateMilestones(Milestones milestones) throws Exception;
	
	Milestones findMilestonesByPkey(BigDecimal pKey);
	
	Milestones findMilestonesByName(BigDecimal pKey,String Name);
	
	Milestones findMilestonesById(BigDecimal pKey,String Id);
	
	public void deleteMilestones(BigDecimal pKey);
	
	public int findMilestoneAssociated(BigDecimal pKey);
}
