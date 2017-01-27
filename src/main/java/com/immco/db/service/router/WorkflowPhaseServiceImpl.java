package com.immco.db.service.router;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.dao.router.MilestoneTemplateDao;
import com.immco.db.model.router.templates.Milestones;
import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.WorkflowPhase;
import com.immco.db.proxy.DCParam;

@Service("milestoneTemplateService")
@Transactional
public class WorkflowPhaseServiceImpl implements WorkflowPhaseService {

	@Autowired
	private MilestoneTemplateDao milestoneDao;
	
	@Autowired
	private DbLogger dbLogger;

	@Override
	public DBDC createMilesoneTemplate(WorkflowPhase workflowPhase) {
		DBDC dbdc = new DBDC();
		try {
			WorkflowPhase templateByID = milestoneDao.findMilestoneTemplateByID(workflowPhase.getWorkflowPhaseId(),
					workflowPhase.getpKey());
			WorkflowPhase templateByName = milestoneDao
					.findMilestoneTemplateByName(workflowPhase.getWorkflowPhaseName(), workflowPhase.getpKey());
			if (templateByID != null) {
				dbdc.setErrorMsg("Phase ID Already Exists");
			} else if (templateByName != null) {
				dbdc.setErrorMsg("Phase Name Already Exists");
			} else {
				milestoneDao.createMilestoneTemplate(workflowPhase);
				dbdc.setSuccessMsg("Phase "+workflowPhase.getWorkflowPhaseName()+" Created");
				dbdc.setInfoMsg(workflowPhase.getpKey().toString());
			}
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, WorkflowPhaseServiceImpl.class);
		}
		return dbdc;
	}

	@Override
	public DBDC updateMilesoneTemplate(WorkflowPhase workflowPhase) throws Exception {
		DBDC dbdc = new DBDC();
		try {
			WorkflowPhase templateByID = milestoneDao.findMilestoneTemplateByID(workflowPhase.getWorkflowPhaseId(),
					workflowPhase.getpKey());
			WorkflowPhase templateByName = milestoneDao
					.findMilestoneTemplateByName(workflowPhase.getWorkflowPhaseName(), workflowPhase.getpKey());
			if (templateByID != null) {
				dbdc.setErrorMsg("Phase ID Already Exists");
			} else if (templateByName != null) {
				dbdc.setErrorMsg("Phase Name Already Exists");
			} else {
				milestoneDao.updateMilestoneTemplate(workflowPhase);
				dbdc.setSuccessMsg("Phase "+workflowPhase.getWorkflowPhaseName()+" Updated");
				dbdc.setInfoMsg(workflowPhase.getpKey().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbdc;
	}

	@Override
	public WorkflowPhase findMilestoneTemplate(DCParam dcParam) {
		BigDecimal pKey = new BigDecimal(dcParam.getpMap().get(WorkflowPhase.PKEY).toString());
		WorkflowPhase milestoneTemplate = milestoneDao.findMilestoneTemplateByPK(pKey);
		return milestoneTemplate;
	}

	@Override
	public List<WorkflowPhase> getAllMilestones() {
		return milestoneDao.getAllMilestones();
	}

	@Override
	public DBDC deleteMilestonetemplate(DCParam dcParam) throws Exception {
		DBDC dbdc=new DBDC();
		BigDecimal pKey=new BigDecimal(dcParam.getpMap().get(WorkflowPhase.PKEY).toString());
		BigDecimal workflowPkey=new BigDecimal(dcParam.getpMap().get(WorkflowPhase.WORKFLOW_PKEY).toString());
		milestoneDao.deleteMilestoneTemplate(pKey,workflowPkey);
		dbdc.setSuccessMsg("Phase Deleted");
		return dbdc;
	}

	@Override
	public DBDC associateMilestoneAndTasks(DCParam dcParam){
		DBDC dbdc=new DBDC();
		List<Integer> taskPkeyList=(List<Integer>) dcParam.getpMap().get(TaskTemplate.LIST_OF_TASKPKEYS);
		BigDecimal milestonePkey= new BigDecimal(dcParam.getpMap().get(TaskTemplate.MILESTONE_PKEY).toString());
		try {
			milestoneDao.associateTaskAndMilestones(milestonePkey, taskPkeyList);
			dbdc.setSuccessMsg("Tasks Added");
		} catch (Exception e) {
			dbLogger.logErrorToJson(e, WorkflowPhaseServiceImpl.class);
		}
		return dbdc;
	}

	@Override
	public DBDC createMilestones(Milestones milestones) throws Exception {
		DBDC dbdc=new DBDC();
		Milestones milestonesById = milestoneDao.findMilestonesById(milestones.getpKey(), milestones.getMilestoneId());
		Milestones milestonesByName = milestoneDao.findMilestonesByName(milestones.getpKey(), milestones.getMilestoneNm());
		if (milestonesById != null) {
			dbdc.setErrorMsg("Milestone ID Already Exists");
		} else if (milestonesByName != null) {
			dbdc.setErrorMsg("Milestone Name Already Exists");
		} else if(milestones.getpKey()==null) {
			milestoneDao.createOrUpdateMilestones(milestones);
			dbdc.setSuccessMsg("Milestone "+milestones.getMilestoneNm()+" Created");
			dbdc.setInfoMsg(milestones.getpKey().toString());
		}
		else{
			milestoneDao.createOrUpdateMilestones(milestones);
			dbdc.setSuccessMsg("Milestone "+milestones.getMilestoneNm()+" Updated");
			dbdc.setInfoMsg(milestones.getpKey().toString());
		}
		return dbdc;
	}

	@Override
	public Milestones findMilestones(DCParam dcParam) {
		BigDecimal pKey= new BigDecimal(dcParam.getpMap().get(Milestones.PKEY).toString());
		Milestones milestones = milestoneDao.findMilestonesByPkey(pKey);
		return milestones;
	}

	@Override
	public DBDC deleteMilestones(DCParam dcParam) {
		DBDC dbdc=new DBDC();
		BigDecimal pKey= new BigDecimal(dcParam.getpMap().get(Milestones.PKEY).toString());
		int count = milestoneDao.findMilestoneAssociated(pKey);
		if(count>0){
			dbdc.setErrorMsg("Milestone is Associated to "+count+" Task(s)");
		}else{
			milestoneDao.deleteMilestones(pKey);
			dbdc.setSuccessMsg("Milestone Deleted");
		}
		return dbdc;
	}

}
