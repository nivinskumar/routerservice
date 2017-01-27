package com.immco.common;

import java.util.HashMap;


public class RouterConstants {

	public static HashMap<Integer, String> phaseIdNameHash = new HashMap<>();
	public static HashMap<Integer, String> queueIdNameHash = new HashMap<>();
	public static HashMap<Integer, String> taskIdNameHash = new HashMap<>();

	static {
		
		taskIdNameHash.put(TaskType.HUMAN_TASK, "HUMAN_TASK");
		taskIdNameHash.put(TaskType.SYSTEM_TASK, "SYSTEM_TASK");
		taskIdNameHash.put(TaskType.EVENT_TASK, "EVENT_TASK");
		taskIdNameHash.put(TaskType.EMAIL_TASK, "EMAIL_TASK");
		taskIdNameHash.put(TaskType.FUNCTION_TASK, "FUNCTION_TASK");
		
		phaseIdNameHash.put(Phase.SERVICEABILITY, "SERVICEABILITY");
		phaseIdNameHash.put(Phase.DESIGN, "DESIGN");
		phaseIdNameHash.put(Phase.CONSTRUCTION, "CONSTRUCTION");
		phaseIdNameHash.put(Phase.QC, "QC");
		phaseIdNameHash.put(Phase.ASBUILT, "ASBUILT");

		queueIdNameHash.put(Queue.SURVEY, "SURVEY");
		queueIdNameHash.put(Queue.WALKOUT, "WALKOUT");
		queueIdNameHash.put(Queue.CONSTRUCTION, "CONSTRUCTION");
		queueIdNameHash.put(Queue.QC, "QC");
		queueIdNameHash.put(Queue.ASBUILT, "ASBUILT");
		queueIdNameHash.put(Queue.RECONCILE, "RECONCILE");
	}

	public static final class TaskType {
		public static final Integer HUMAN_TASK = 100;
		public static final Integer SYSTEM_TASK = 110;
		public static final Integer EVENT_TASK = 120;
		public static final Integer EMAIL_TASK = 130;
		public static final Integer FUNCTION_TASK = 140;
		public static final Integer APPROVAL_TASK = 150;
		public static final Integer CONDITIONAL_TASK = 160;
	}

	public static final class Phase {
		public static final Integer SERVICEABILITY = 1;
		public static final Integer DESIGN = 2;
		public static final Integer CONSTRUCTION = 3;
		public static final Integer QC = 4;
		public static final Integer ASBUILT = 5;

	}

	public static final class Queue {
		public static final Integer SURVEY = 1000;
		public static final Integer WALKOUT = 1100;
		public static final Integer FIBER_DESIGN = 1200;
		public static final Integer COAX_DESIGN = 1300;
		public static final Integer CONSTRUCTION = 1400;
		public static final Integer QC = 1500;
		public static final Integer ASBUILT = 1600;
		public static final Integer RECONCILE = 1700;
	}

	public static final class WorkFlowExecOutcome {
		public static final String IN_PROGRESS = "IN_PROGRESS";
		public static final String ON_HOLD = "ON_HOLD";
		public static final String JOB_CANCELLED = "JOB_CANCELLED";
		public static final String EXECUTION_COMPLETED = "EXECUTION_COMPLETED";
	}

	public static final class MilestoneStatus {
		public static final String NOT_STARTED = "NOT_STARTED";
		public static final String IN_PROGRESS = "IN_PROGRESS";
		public static final String ON_HOLD = "ON_HOLD";
		public static final String JOB_CANCELLED = "JOB_CANCELLED";
		public static final String EXECUTION_COMPLETED = "EXECUTION_COMPLETED";
	}
}