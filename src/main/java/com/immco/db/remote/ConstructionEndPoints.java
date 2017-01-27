package com.immco.db.remote;

public class ConstructionEndPoints {
    private final static String auditableUserId = "/{auditableUserId}";

    /**
     * Controller for Basic, Generic Operations
     */
    public static final class BASICCONTROLLER {
	public static final String BASE_URL = "/dbservice/basic";
	public static final String EXECUTE_SQL_PGCONFIG = auditableUserId + "/execselect/pfconfig";
	public static final String EXECUTE_SQL_PLAIN = auditableUserId + "/execselect/plain";
	// shajee.. the below is the latest
	public static final String EXECUTE_RAW_SQL = auditableUserId + "/execselect/raw";
	public static final String EXECUTE_RAW_UPDATE_SQL = auditableUserId + "/execselect/sqlupdate";

    }

    public static final class ORGCONTROLLER {
	public static final String BASE_URL = "/dbservice/org";
	public static final String CREATE_ORG = auditableUserId + "/createorg";
	public static final String FIND_ORG = auditableUserId + "/findorg";
	public static final String DELETE_ORG = auditableUserId + "/deleteorg";
    }

    /**
     * Controller for operations against USER Objects
     */
    public static final class USERCONTROLLER {
	public static final String BASE_URL = "/dbservice/user";
	public static final String CREATE_OR_UPDATE_USER = auditableUserId + "/createorupdateuser";
	public static final String FIND_USER = auditableUserId + "/finduser";
	public static final String DELETE_USER = auditableUserId + "/deleteuser";
	public static final String FIND_NEW_USER_REQUEST = auditableUserId + "/findNewUserRequest";
    }

    public static final class HUBCONTROLLER {
	public static final String BASE_URL = "/dbservice/hub";
	public static final String CREATE_OR_UPDATE_HUB = auditableUserId + "/createorupdatehub";
	public static final String FIND_HUB = auditableUserId + "/findhub";
	public static final String DELETE_HUB = auditableUserId + "/deletehub";
    }

    public static final class NODECONTROLLER {
	public static final String BASE_URL = "/dbservice/node";
	public static final String CREATE_OR_UPDATE_NODE = auditableUserId + "/createorupdatenode";
	public static final String FIND_NODE = auditableUserId + "/findnode";
	public static final String DELETE_NODE = auditableUserId + "/deletenode";
    }

    public static final class CONTRACTORCONTROLLER {
	public static final String BASE_URL = "/dbservice/contractor";
	public static final String CREATE_OR_UPDATE_CONTRACTOR = auditableUserId + "/createorupdatecontractor";
	public static final String FIND_CONTRACTOR = auditableUserId + "/findcontractor";
	public static final String DELETE_CONTRACTOR = auditableUserId + "/deletecontractor";
    }

    public static final class JOBCONTROLLER {
	public static final String BASE_URL = "/dbservice/job";
	public static final String CREATE_OR_UPDATE_JOB = auditableUserId + "/createorupdatejob";
	public static final String FIND_JOB = auditableUserId + "/findjob";
	public static final String DELETE_JOB = auditableUserId + "/deletejob";
    }

    public static final class CONSTRUCTIONCONTROLLER {
	public static final String BASE_URL = "/dbservice/construction";
	public static final String CREATE_OR_UPDATE_CONSTRUCTION = auditableUserId + "/createorupdateconstruction";
	public static final String FIND_CONSTRUCTION = auditableUserId + "/findconstruction";
	public static final String DELETE_CONSTRUCTION = auditableUserId + "/deleteconstruction";
    }

    public static final class FOOTAGECONTROLLER {
	public static final String BASE_URL = "/dbservice/footage";
	public static final String CREATE_OR_UPDATE_FOOTAGE = auditableUserId + "/createorupdatefootage";
	public static final String FIND_FOOTAGE = auditableUserId + "/findfootage";
	public static final String DELETE_FOOTAGE = auditableUserId + "/deletefootage";
    }

    public static final class ATTACHMENTSCONTROLLER {
	public static final String BASE_URL = "/dbservice/attachments";
	public static final String CREATE_OR_UPDATE_ATTACHMENTS = auditableUserId + "/createorupdateattachments";
	public static final String FIND_ATTACHMENTS = auditableUserId + "/findattachments";
	public static final String DELETE_ATTACHMENTS = auditableUserId + "/deleteattachments";
    }

    public static final class JOBCOMMENTSCONTROLLER {
	public static final String BASE_URL = "/dbservice/jobcomments";
	public static final String CREATE_OR_UPDATE_JOBCOMMENTS = auditableUserId + "/createorupdatejobcomments";
	public static final String FIND_JOBCOMMENTS = auditableUserId + "/findjobcomments";
	public static final String DELETE_JOBCOMMENTS = auditableUserId + "/deletejobcomments";
    }

    public static final class ADDRESSINGCONTROLLER {
	public static final String BASE_URL = "/dbservice/addressing";
	public static final String CREATE_OR_UPDATE_ADDRESSING = auditableUserId + "/createorupdateaddressing";
	public static final String FIND_ADDRESSING = auditableUserId + "/findaddressing";
	public static final String DELETE_ADDRESSING = auditableUserId + "/deleteaddressing";
    }

    public static final class ADDRESSINGUPLOADCONTROLLER {
	public static final String BASE_URL = "/dbservice/addressingupload";
	public static final String CREATE_OR_UPDATE_ADDRESSINGUPLOAD = auditableUserId + "/createorupdateaddressingupload";
	public static final String FIND_ADDRESSINGUPLOAD = auditableUserId + "/findaddressingupload";
	public static final String DELETE_ADDRESSINGUPLOAD = auditableUserId + "/deleteaddressingupload";
    }

    public static final class SFINTAKECONTROLLER {
	public static final String BASE_URL = "/dbservice/sfintake";
	public static final String CREATE_OR_UPDATE_SFINTAKE = auditableUserId + "/createorupdatesfintake";
	public static final String FIND_SFINTAKE = auditableUserId + "/findsfintake";
	public static final String DELETE_SFINTAKE = auditableUserId + "/deletesfintake";
    }

    public static final class GEOCONTROLLER {
	public static final String BASE_URL = "/dbservice/geo";
	public static final String CREATE_OR_UPDATE_GEO = auditableUserId + "/createorupdategeo";
    }

    public static final class T3INTAKECONTROLLER {
	public static final String BASE_URL = "/dbservice/t3intake";
	public static final String CREATE_OR_UPDATE_T3INTAKE = auditableUserId + "/createorupdatet3intake";
	public static final String FIND_T3INTAKE = auditableUserId + "/findt3intake";
	public static final String DELETE_T3INTAKE = auditableUserId + "/deletet3intake";
    }
}