package com.immco.db.model.router.exec;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.immco.db.model.IPersistable;

@Entity
@Table(name = "task_exec", schema = "router")
public class TaskExec implements IPersistable {
	
	
	public static final String CTX_FIND_FIRST = "CTX_FIND_FIRST";
	public static final String CTX_FIND_LAST = "CTX_FIND_LAST";
	public static final String CTX_FIND_NEXT = "CTX_FIND_NEXT";
	public static final String CTX_FIND_PREVIOUS = "CTX_FIND_PREVIOUS";
	public static final String CTX_FIND_CURRENT = "CTX_FIND_CURRENT";
	
	public static final String PKEY="pKey";
	public static final String CWS_ID="cwsId";
	public static final String PRED_TASK_ID="predecessorTaskId";
	public static final String SUCC_TASK_ID="successorTaskId";
	public static final String TASK_ID="taskId";
	
	public static final String TASK_PICKUP_TIME = "taskPickupTime";
	public static final String TASK_COMPLETED_TIME = "taskCompletedTime";

	private BigDecimal pKey;
	private BigDecimal workflowPhaseExecPkey;
	private String workflowPhaseName;
	private String workorderId;
	private Integer cwsId;
	private String taskId;
	private String taskNm;
	private String taskType;
	private String taskDesc;
	private String uiBean;
	private String endPoint;
	private String successorTaskId;
	private String predecessorTaskId;
	private Integer slaExec;
	private Integer slaMetrics;
	private Long slaStartTime;
	private Long slaEndTime;
	private boolean outOfSla;
	private String phaseName;
	private String queueName;
	private Integer slaPickupInMin;
	private Long taskPickupTime;
	private String taskAssignedBy;
	private Long taskLandedTime;
	private Long taskCompletedTime;
	private String taskComments;
	private boolean productivitySet;
	private Integer productivityMin;
	private String taskOwnerid;
	private String taskOwnerName;
	private boolean isIgnoreSla;
	private String rgnNm;
	private String maNm;
	private Integer glid;
	private Integer psid;
	private BigDecimal rolePkey;
	private BigDecimal workGroupPkey;
	private Integer orgLevel;
	
	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_TASK_EXEC", sequenceName = "SQ_TASK_EXEC", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TASK_EXEC")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "WORKFLOW_PHASE_EXEC_PKEY", nullable = false)
	public BigDecimal getworkflowPhaseExecPkey() {
		return workflowPhaseExecPkey;
	}

	@Column(name = "WORKORDER_ID", nullable = false)
	public String getWorkorderId() {
		return workorderId;
	}

	@Column(name = "CWS_ID", nullable = false)
	public Integer getCwsId() {
		return cwsId;
	}

	@Column(name = "TASK_ID", nullable = false)
	public String getTaskId() {
		return taskId;
	}

	@Column(name = "TASK_NM", nullable = false)
	public String getTaskNm() {
		return taskNm;
	}

	@Column(name = "TASK_TYPE", nullable = false)
	public String getTaskType() {
		return taskType;
	}

	@Column(name = "TASK_DESC")
	public String getTaskDesc() {
		return taskDesc;
	}

	@Column(name = "UI_BEAN")
	public String getUiBean() {
		return uiBean;
	}

	@Column(name = "END_POINT")
	public String getEndPoint() {
		return endPoint;
	}

	@Column(name = "SUCC_TASK_ID")
	public String getSuccessorTaskId() {
		return successorTaskId;
	}

	@Column(name = "PRED_TASK_ID")
	public String getPredecessorTaskId() {
		return predecessorTaskId;
	}

	@Column(name = "SLA_EXEC")
	public Integer getSlaExec() {
		return slaExec;
	}

	@Column(name = "SLA_METRICS")
	public Integer getSlaMetrics() {
		return slaMetrics;
	}

	@Column(name = "SLA_START_TIME")
	public Long getSlaStartTime() {
		return slaStartTime;
	}

	@Column(name = "SLA_END_TIME")
	public Long getSlaEndTime() {
		return slaEndTime;
	}

	@Column(name = "IS_OUT_OF_SLA")
	public boolean isOutOfSla() {
		return outOfSla;
	}

	@Column(name = "PHASE_NM", nullable = false)
	public String getPhaseName() {
		return phaseName;
	}

	@Column(name = "QUEUE_NM", nullable = false)
	public String getQueueName() {
		return queueName;
	}

	@Column(name = "SLA_PICKUP_IN_MIN")
	public Integer getSlaPickupInMin() {
		return slaPickupInMin;
	}

	@Column(name = "TASK_PICKUP_TIME")
	public Long getTaskPickupTime() {
		return taskPickupTime;
	}

	@Column(name = "TASK_ASSIGNED_BY")
	public String getTaskAssignedBy() {
		return taskAssignedBy;
	}

	@Column(name = "TASK_LANDED_TIME")
	public Long getTaskLandedTime() {
		return taskLandedTime;
	}

	@Column(name = "TASK_COMPLETED_TIME")
	public Long getTaskCompletedTime() {
		return taskCompletedTime;
	}

	@Column(name = "TASK_COMMENTS")
	public String getTaskComments() {
		return taskComments;
	}

	@Column(name = "HAS_PRODUCTIVITY", nullable = false)
	public boolean isProductivitySet() {
		return productivitySet;
	}

	@Column(name = "PRODUCTIVITY_MIN")
	public Integer isProductivityMin() {
		return productivityMin;
	}

	@Column(name = "TASK_OWNER_ID")
	public String getTaskOwnerid() {
		return taskOwnerid;
	}

	@Column(name = "TASK_OWNER_NM")
	public String getTaskOwnerName() {
		return taskOwnerName;
	}

	@Column(name = "IS_IGNORE_SLA")
	public boolean isIgnoreSla() {
		return isIgnoreSla;
	}

	@Column(name = "REGION_NM", nullable = false)
	public String getRgnNm() {
		return rgnNm;
	}

	@Column(name = "MA_NM", nullable = false)
	public String getMaNm() {
		return maNm;
	}

	@Column(name = "GLID_NO", nullable = false)
	public Integer getGlid() {
		return glid;
	}

	@Column(name = "PSID_NO", nullable = false)
	public Integer getPsid() {
		return psid;
	}
	
	@Column(name = "WORKFLOW_PHASE_NM", nullable = false)
	public String getWorkflowPhaseName() {
		return workflowPhaseName;
	}
	
	@Column(name = "ROLE_PKEY", nullable = true)
	public BigDecimal getRolePkey() {
		return rolePkey;
	}

	@Column(name = "WORK_GROUP_PKEY", nullable = true)
	public BigDecimal getWorkGroupPkey() {
		return workGroupPkey;
	}

	@Column(name = "ORG_LEVEL", nullable = true)
	public Integer getOrgLevel() {
		return orgLevel;
	}
	
	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setWorkflowPhaseExecPkey(BigDecimal miletsoneExecPkey) {
		this.workflowPhaseExecPkey = miletsoneExecPkey;
	}

	public void setWorkorderId(String workorderId) {
		this.workorderId = workorderId;
	}

	public void setCwsId(Integer cwsId) {
		this.cwsId = cwsId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTaskNm(String taskNm) {
		this.taskNm = taskNm;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public void setUiBean(String uiBean) {
		this.uiBean = uiBean;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public void setSuccessorTaskId(String successorTaskId) {
		this.successorTaskId = successorTaskId;
	}

	public void setPredecessorTaskId(String predecessorTaskId) {
		this.predecessorTaskId = predecessorTaskId;
	}

	public void setSlaExec(Integer slaExec) {
		this.slaExec = slaExec;
	}

	public void setSlaMetrics(Integer slaMetrics) {
		this.slaMetrics = slaMetrics;
	}

	public void setSlaStartTime(Long slaStartTime) {
		this.slaStartTime = slaStartTime;
	}

	public void setSlaEndTime(Long slaEndTime) {
		this.slaEndTime = slaEndTime;
	}

	public void setOutOfSla(boolean outOfSla) {
		this.outOfSla = outOfSla;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public void setSlaPickupInMin(Integer slaPickupInMin) {
		this.slaPickupInMin = slaPickupInMin;
	}

	public void setTaskPickupTime(Long taskPickupTime) {
		this.taskPickupTime = taskPickupTime;
	}

	public void setTaskAssignedBy(String taskAssignedBy) {
		this.taskAssignedBy = taskAssignedBy;
	}

	public void setTaskLandedTime(Long taskLandedTime) {
		this.taskLandedTime = taskLandedTime;
	}

	public void setTaskCompletedTime(Long taskCompletedTime) {
		this.taskCompletedTime = taskCompletedTime;
	}

	public void setTaskComments(String taskComments) {
		this.taskComments = taskComments;
	}

	public void setProductivitySet(boolean productivitySet) {
		this.productivitySet = productivitySet;
	}

	public void setProductivityMin(Integer productivityMin) {
		this.productivityMin = productivityMin;
	}

	public void setTaskOwnerid(String taskOwnerid) {
		this.taskOwnerid = taskOwnerid;
	}

	public void setTaskOwnerName(String taskOwnerName) {
		this.taskOwnerName = taskOwnerName;
	}

	public void setIgnoreSla(boolean isIgnoreSla) {
		this.isIgnoreSla = isIgnoreSla;
	}

	public void setRgnNm(String rgnNm) {
		this.rgnNm = rgnNm;
	}

	public void setMaNm(String maNm) {
		this.maNm = maNm;
	}

	public void setGlid(Integer glid) {
		this.glid = glid;
	}

	public void setPsid(Integer psid) {
		this.psid = psid;
	}

	public void setWorkflowPhaseName(String workflowPhaseName) {
		this.workflowPhaseName = workflowPhaseName;
	}
	
	public void setRolePkey(BigDecimal rolePkey) {
		this.rolePkey = rolePkey;
	}

	public void setWorkGroupPkey(BigDecimal workGroupPkey) {
		this.workGroupPkey = workGroupPkey;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}


}
