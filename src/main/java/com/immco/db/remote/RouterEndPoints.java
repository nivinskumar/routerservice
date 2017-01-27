package com.immco.db.remote;

/**
 * Constant definitions
 * 
 * @author shajeelawrence
 *
 */
public class RouterEndPoints {
	private final static String auditableUserId = "/{auditableUserId}";

	public static final class WORKFLOWTEMPLATECONTROLLER {
		public static final String BASE_URL = "/routerservice/workflowtemplate";
		public static final String CREATE_OR_UPDATE_WORKFLOWTEMPLATE = auditableUserId
				+ "/createorupdateworkflowtemplate";
		public static final String FIND_WORKFLOWTEMPLATE = auditableUserId + "/findworkflowtemplate";
		public static final String DELETE_WORKFLOWTEMPLATE = auditableUserId + "/deleteworkflowtemplate";
	}

	public static final class ROLECONTROLLER {
		public static final String BASE_URL = "/routerservice/role";
		public static final String CREATE_OR_UPDATE_ROLE = auditableUserId + "/createorupdaterole";
		public static final String FIND_ROLE = auditableUserId + "/findrole";
		public static final String DELETE_ROLE = auditableUserId + "/deleterole";
	}

	public static final class TASKTEMPLATECONTROLLER {
		public static final String BASE_URL = "/routerservice/tasktemplate";
		public static final String CREATE_OR_UPDATE_TASKTEMPLATE = auditableUserId + "/createorupdatetasktemplate";
		public static final String FIND_TASKTEMPLATE = auditableUserId + "/findtasktemplate";
		public static final String DELETE_TASKTEMPLATE = auditableUserId + "/deletetasktemplate";
	}

	public static final class MILESTONETEMPLATECONTROLLER {
		public static final String BASE_URL = "/routerservice/milestonetemplate";
		public static final String CREATE_OR_UPDATE_MILESTONETEMPLATE = auditableUserId
				+ "/createorupdatemilestonetemplate";
		public static final String FIND_MILESTONETEMPLATE = auditableUserId + "/findmilestonetemplate";
		public static final String DELETE_MILESTONETEMPLATE = auditableUserId + "/deletemilestonetemplate";
	}

	public static final class APPFUNCTIONCONTROLLER {
		public static final String BASE_URL = "/routerservice/appfunctioncontroller";
		public static final String CREATE_OR_UPDATE_APPFUNCTIONCONTROLLER = auditableUserId
				+ "/createorupdateappfunctioncontroller";
		public static final String FIND_APPFUNCTIONCONTROLLER = auditableUserId + "/findappfunctioncontroller";
		public static final String DELETE_APPFUNCTIONCONTROLLER = auditableUserId + "/deleteappfunctioncontroller";
	}

	public static final class ROLETEMPLATEAPPFUNMAPCONTROLLER {
		public static final String BASE_URL = "/routerservice/roletemplateappfunmapcontroller";
		public static final String CREATE_OR_UPDATE_ROLETEMPLATEAPPFUNMAPCONTROLLER = auditableUserId
				+ "/createorupdateroletemplateappfunmapcontroller";
		public static final String FIND_ROLETEMPLATEAPPFUNMAPCONTROLLER = auditableUserId
				+ "/findroletemplateappfunmapcontroller";
		public static final String DELETE_ROLETEMPLATEAPPFUNMAPCONTROLLER = auditableUserId
				+ "/deleteroletemplateappfunmapcontroller";
	}

	public static final class APPUSERROLEMAPCONTROLLER {
		public static final String BASE_URL = "/routerservice/appuserrolemapcontroller";
		public static final String CREATE_OR_UPDATE_APPUSERROLEMAPCONTROLLER = auditableUserId
				+ "/createorupdateappuserrolemapcontroller";
		public static final String FIND_APPUSERROLEMAPCONTROLLER = auditableUserId + "/findappuserrolemapcontroller";
		public static final String DELETE_APPUSERROLEMAPCONTROLLER = auditableUserId
				+ "/deleteappuserrolemapcontroller";
	}

	public static final class ROLETEMPLATECONTROLLER {
		public static final String BASE_URL = "/routerservice/roletemplatecontroller";
		public static final String CREATE_OR_UPDATE_ROLETEMPLATECONTROLLER = auditableUserId
				+ "/createorupdateroletemplatecontroller";
		public static final String FIND_ROLETEMPLATECONTROLLER = auditableUserId + "/findroletemplatecontroller";
		public static final String DELETE_ROLETEMPLATECONTROLLER = auditableUserId + "/deleteroletemplatecontroller";
	}

	public static final class SUBTASKTEMPLATECONTROLLER {
		public static final String BASE_URL = "/routerservice/subtasktemplate";
		public static final String CREATE_OR_UPDATE_SUBTASKTEMPLATE = auditableUserId
				+ "/createorupdatesubtasktemplate";
		public static final String FIND_SUBTASKTEMPLATE = auditableUserId + "/findsubtasktemplate";
		public static final String DELETE_SUBTASKTEMPLATE = auditableUserId + "/deletesubtasktemplate";
	}

	public static final class USERDEFINEDFORMTEMPLATECONTROLLER {
		public static final String BASE_URL = "/routerservice/userdefinedformtemplate";
		public static final String CREATE_OR_UPDATE_USERDEFINEDFORMTEMPLATE = auditableUserId
				+ "/createorupdateuserdefinedformtemplate";
		public static final String FIND_USERDEFINEDFORMTEMPLATE = auditableUserId + "/finduserdefinedformtemplate";
		public static final String DELETE_USERDEFINEDFORMTEMPLATE = auditableUserId + "/deleteuserdefinedformtemplate";
	}

	public static final class USERDEFINEDCUSTOMFORMEXECUTIONCONTROLLER {
		public static final String BASE_URL = "/routerservice/userdefinedcustomformexecution";
		public static final String CREATE_OR_UPDATE_USERDEFINEDCUSTOMFORMEXECUTION = auditableUserId
				+ "/createorupdateuserdefinedcustomformexecution";
		public static final String FIND_USERDEFINEDCUSTOMFORMEXECUTION = auditableUserId
				+ "/finduserdefinedcustomformexecution";
		public static final String DELETE_USERDEFINEDCUSTOMFORMEXECUTION = auditableUserId
				+ "/deleteuserdefinedcustomformexecution";
	}
	
	public static final class TASKEXECUTIONCONTROLLER{
		public static final String BASE_URL = "/routerservice/taskexecution";   
		public static final String CREATE_OR_UPDATE_TASKEXECUTION=auditableUserId+"/createorupdatetaskexecution";
		public static final String FIND_TASKEXECUTION=auditableUserId+"/findtaskexecution";
		public static final String DELETE_TASKEXECUTION=auditableUserId+"/deletetaskexecution";
	}
}
