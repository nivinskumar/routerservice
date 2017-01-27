package com.immco.routing;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.immco.common.DBDC;
import com.immco.common.RouterConstants;
import com.immco.db.model.router.templates.WorkflowPhase;
import com.immco.db.proxy.DCParam;
import com.immco.db.model.router.templates.SubTaskTemplate;
import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.WorkFlowTemplate;
import com.immco.db.service.router.WorkflowPhaseService;
import com.immco.db.service.router.SubTaskTemplateService;
import com.immco.db.service.router.TaskTemplateService;
import com.immco.db.service.router.WorkflowTemplateService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RoutingApplication.class)
@WebAppConfiguration
public class WorkflowCreator {

	@Autowired
	private WorkflowTemplateService wfTemplateService;
	@Autowired
	WorkflowPhaseService milestoneTemplateService;
	@Autowired
	TaskTemplateService taskTemplateService;
	@Autowired
	SubTaskTemplateService subTaskTemplateService;

	public void createWorkflowTemplate() {
		WorkFlowTemplate w = new WorkFlowTemplate();
		w.setConstructionTypePkey(new BigDecimal(1));
		w.setCrtById(1035);
		w.setCrtByName("Steve");
		w.setWorkflowDesc("Workflow for SMB Single Site");
		w.setWorkflowId("WF-SMB-1");
		w.setWorkflowName("SMB SINGLE SITE");
		try {
			wfTemplateService.createWorkflowTemplate(w);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createMileStoneTemplates() {
		// 1
		WorkflowPhase mt1 = new WorkflowPhase();
		mt1.setWorkflowPhaseName("SURVEY INTAKE");
		mt1.setWorkflowPhaseDesc("Completion of Survey Intake");
		mt1.setWorkflowPhaseId("SURVEY");
		mt1.setSeqNo(1);
		mt1.setSla(4);
		mt1.setSlaMetrics(WorkflowPhase.SLA_METRICS_HOURS);
		mt1.setWorkflowTemplatePkey(new BigDecimal(1));

		// 2
		WorkflowPhase mt2 = new WorkflowPhase();
		mt2.setWorkflowPhaseName("SURVEY ESTIMATION");
		mt2.setWorkflowPhaseDesc("Estimation of Survey");
		mt2.setWorkflowPhaseId("ESTIMATION");
		mt2.setSeqNo(1);
		mt2.setSla(2);
		mt2.setSlaMetrics(WorkflowPhase.SLA_METRICS_DAYS);
		mt2.setWorkflowTemplatePkey(new BigDecimal(1));

		// 2
		WorkflowPhase mt3 = new WorkflowPhase();
		mt3.setWorkflowPhaseName("ROI");
		mt3.setWorkflowPhaseDesc("Completion of Survey Intake");
		mt3.setWorkflowPhaseId("ROI");
		mt3.setSeqNo(1);
		mt3.setSla(4);
		mt3.setSlaMetrics(WorkflowPhase.SLA_METRICS_HOURS);
		mt3.setWorkflowTemplatePkey(new BigDecimal(1));

		try {
			milestoneTemplateService.createMilesoneTemplate(mt1);
			milestoneTemplateService.createMilesoneTemplate(mt2);
			milestoneTemplateService.createMilesoneTemplate(mt3);

			System.out.println("Milestones Created...........");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createTasks() throws Exception {

		try {
			// 3
			TaskTemplate t1m1 = new TaskTemplate();
			t1m1.setEndPoint("End Point M1T1");
			t1m1.setMilestonePkey(new BigDecimal(1));
			t1m1.setOrgLevel(3);
			t1m1.setPhaseId(RouterConstants.Phase.SERVICEABILITY);
			t1m1.setQueueId(RouterConstants.Queue.SURVEY);
			t1m1.setRolePkey(new BigDecimal(2));
			t1m1.setSeqNo(1);
			t1m1.setTaskDesc("SURVEY");
			t1m1.setTaskId("M1T1");
			t1m1.setTaskName("SURVEY");
			t1m1.setTaskType(RouterConstants.TaskType.HUMAN_TASK);
			t1m1.setUiBean("com.immco.ui.surveyUI");
			
			DCParam dcParam = new DCParam();
			taskTemplateService.createOrUpdateTaskTemplate(t1m1, dcParam);

			SubTaskTemplate s1t1m1 = new SubTaskTemplate();
			s1t1m1.setConcurrent(false);
			s1t1m1.setPhaseId(RouterConstants.Phase.SERVICEABILITY);
			s1t1m1.setQueueId(RouterConstants.Queue.SURVEY);
			s1t1m1.setSeqNo(1);
			s1t1m1.setSubTaskDesc("UPLOAD MAP");
			s1t1m1.setSubTaskId("S1T1M1");
			s1t1m1.setSubTaskNm("MAP UPLOAD");
			s1t1m1.setTaskType(RouterConstants.TaskType.HUMAN_TASK);
			s1t1m1.setTaskPkey(new BigDecimal(3));
			subTaskTemplateService.createOrUpdateSubTaskTemplate(s1t1m1);

			SubTaskTemplate s2t1m1 = new SubTaskTemplate();
			s2t1m1.setConcurrent(true);
			s2t1m1.setPhaseId(RouterConstants.Phase.SERVICEABILITY);
			s2t1m1.setQueueId(RouterConstants.Queue.SURVEY);
			s2t1m1.setSeqNo(2);
			s2t1m1.setSubTaskDesc("S2T1M1");
			s2t1m1.setSubTaskId("S2T1M1");
			s2t1m1.setSubTaskNm("S2T1M1");
			s2t1m1.setTaskType(RouterConstants.TaskType.HUMAN_TASK);
			s2t1m1.setTaskPkey(new BigDecimal(3));
			subTaskTemplateService.createOrUpdateSubTaskTemplate(s2t1m1);

			SubTaskTemplate s3t1m1 = new SubTaskTemplate();
			s3t1m1.setEndPoint("s2t1m1 Endpoint");
			s3t1m1.setUiBean("s2t1m1 UIBean");
			s3t1m1.setConcurrent(false);
			s3t1m1.setPhaseId(RouterConstants.Phase.SERVICEABILITY);
			s3t1m1.setQueueId(RouterConstants.Queue.SURVEY);
			s3t1m1.setSeqNo(3);
			s3t1m1.setSubTaskDesc("S3T1M1");
			s3t1m1.setSubTaskId("S3T1M1");
			s3t1m1.setSubTaskNm("S3T1M1");
			s3t1m1.setTaskType(RouterConstants.TaskType.FUNCTION_TASK);
			s3t1m1.setTaskPkey(new BigDecimal(3));
			s3t1m1.setPredessorPkey(new BigDecimal(2));
			subTaskTemplateService.createOrUpdateSubTaskTemplate(s3t1m1);
			s1t1m1.setSuccessorPkey(s3t1m1.getpKey());
			subTaskTemplateService.createOrUpdateSubTaskTemplate(s1t1m1);

			// 4
			TaskTemplate t2m1 = new TaskTemplate();
			t2m1.setMilestonePkey(new BigDecimal(1));
			t2m1.setOrgLevel(3);
			t2m1.setPhaseId(RouterConstants.Phase.SERVICEABILITY);
			t2m1.setQueueId(RouterConstants.Queue.SURVEY);
			t2m1.setRolePkey(new BigDecimal(2));
			t2m1.setSeqNo(2);
			t2m1.setTaskDesc("ASSIGN FIELD ENGINEER");
			t2m1.setTaskId("M1T2");
			t2m1.setTaskName("ASSIGN FIELD ENGINEER");
			t2m1.setTaskType(RouterConstants.TaskType.FUNCTION_TASK);
			t2m1.setPredessorPkey(t1m1.getpKey());
			taskTemplateService.createOrUpdateTaskTemplate(t2m1,dcParam);
			t2m1.setSuccessorPkey(t2m1.getpKey());
			taskTemplateService.createOrUpdateTaskTemplate(t1m1,dcParam);

			// 5
			TaskTemplate t3m1 = new TaskTemplate();
			t3m1.setMilestonePkey(new BigDecimal(1));
			t3m1.setOrgLevel(3);
			t3m1.setPhaseId(RouterConstants.Phase.SERVICEABILITY);
			t3m1.setQueueId(RouterConstants.Queue.SURVEY);
			t3m1.setRolePkey(new BigDecimal(2));
			t3m1.setSeqNo(3);
			t3m1.setTaskDesc("ASSIGN NODE");
			t3m1.setTaskId("M1T3");
			t3m1.setTaskName("ASSIGN NODE TO JOB");
			t3m1.setTaskType(RouterConstants.TaskType.FUNCTION_TASK);
			t3m1.setPredessorPkey(t2m1.getpKey());
			taskTemplateService.createOrUpdateTaskTemplate(t3m1,dcParam);
			t2m1.setSuccessorPkey(t3m1.getpKey());
			taskTemplateService.createOrUpdateTaskTemplate(t2m1,dcParam);

			///////////////////////////////////////////

			// 6
			TaskTemplate t1m2 = new TaskTemplate();
			t1m2.setEndPoint("End Point M2T1");
			t1m2.setMilestonePkey(new BigDecimal(2));
			t1m2.setOrgLevel(1);
			t1m2.setPhaseId(RouterConstants.Phase.SERVICEABILITY);
			t1m2.setQueueId(RouterConstants.Queue.SURVEY);
			t1m2.setRolePkey(new BigDecimal(2));
			t1m2.setSeqNo(1);
			t1m2.setTaskDesc("ADD STD LABOR & MATERIAL TO ESTIMATION");
			t1m2.setTaskId("M2T1");
			t1m2.setTaskName("ESTIMATION");
			t1m2.setTaskType(RouterConstants.TaskType.HUMAN_TASK);
			t1m2.setUiBean("com.immco.ui.surveyUI");
			taskTemplateService.createOrUpdateTaskTemplate(t1m2,dcParam);

			// 7
			TaskTemplate t2m2 = new TaskTemplate();
			t2m2.setMilestonePkey(new BigDecimal(2));
			t2m2.setOrgLevel(1);
			t2m2.setPhaseId(RouterConstants.Phase.SERVICEABILITY);
			t2m2.setQueueId(RouterConstants.Queue.SURVEY);
			t2m2.setRolePkey(new BigDecimal(2));
			t2m2.setSeqNo(2);
			t2m2.setTaskDesc("REQUEST FOR CPR");
			t2m2.setTaskId("M2T2");
			t2m2.setTaskName("CPR REQUEST");
			t2m2.setTaskType(RouterConstants.TaskType.FUNCTION_TASK);
			t2m2.setUiBean("1000.1");
			t2m2.setPredessorPkey(t1m2.getpKey());
			taskTemplateService.createOrUpdateTaskTemplate(t2m2,dcParam);
			t1m2.setPredessorPkey(t2m2.getpKey());

			///////////////////////////////////////////////

			// 9
			TaskTemplate t1m3 = new TaskTemplate();
			t1m3.setEndPoint("End Point M3T1");
			t1m3.setMilestonePkey(new BigDecimal(4));
			t1m3.setOrgLevel(1);
			t1m3.setPhaseId(RouterConstants.Phase.SERVICEABILITY);
			t1m3.setQueueId(RouterConstants.Queue.SURVEY);
			t1m3.setRolePkey(new BigDecimal(2));
			t1m3.setSeqNo(1);
			t1m3.setTaskDesc("CALCULATE ROI");
			t1m3.setTaskId("M3T1");
			t1m3.setTaskName("ROI");
			t1m3.setTaskType(RouterConstants.TaskType.HUMAN_TASK);
			t1m3.setUiBean("com.immco.ui.surveyUI");
			taskTemplateService.createOrUpdateTaskTemplate(t1m3,dcParam);

			System.out.println("Done...............");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
