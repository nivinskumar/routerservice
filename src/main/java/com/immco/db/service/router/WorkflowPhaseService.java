package com.immco.db.service.router;

import java.util.List;

import com.immco.common.DBDC;
import com.immco.db.model.router.templates.Milestones;
import com.immco.db.model.router.templates.WorkflowPhase;
import com.immco.db.proxy.DCParam;

public interface WorkflowPhaseService {

	DBDC createMilesoneTemplate(WorkflowPhase milestoneTemplate) throws Exception;

	DBDC updateMilesoneTemplate(WorkflowPhase milestoneTemplate) throws Exception;

	WorkflowPhase findMilestoneTemplate(DCParam dcParam);

	List<WorkflowPhase> getAllMilestones();
	
	DBDC deleteMilestonetemplate(DCParam dcParam) throws Exception;
	
	DBDC associateMilestoneAndTasks(DCParam dcParam) throws Exception;
	
	DBDC createMilestones(Milestones milestones) throws Exception;
	
	Milestones findMilestones(DCParam dcParam);
	
	DBDC deleteMilestones(DCParam dcParam);
}
