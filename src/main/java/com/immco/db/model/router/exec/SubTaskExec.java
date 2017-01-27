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
@Table(name = "subtask_exec", schema = "router")
public class SubTaskExec implements IPersistable {

	private BigDecimal pKey;
	private BigDecimal taskExecPkey;
	private String workorderId;
	private Integer cwsId;
	private String subTaskId;
	private String subTaskName;
	private String subTaskType;
	private String subTaskDesc;
	private String taskId;
	private String taskName;
	private String uiBean;
	private String endPoint;
	private Integer slaExec;
	private Integer slaMetrics;
	private Long slaStartTime;
	private Long slaEndTime;
	private boolean outOfSla;
	private String phaseName;
	private String queueName;
	private String subTaskAssignedBy;
	private Long subTaskAssignedTime;
	private Long subTaskCompletedTime;
	private String subTaskAssignedTo;
	private boolean productivitySet;
	private Integer productivityHrs;
	private boolean ignoreSla;
	private String regionName;
	private String maName;
	private Integer glid;
	private Integer psid;

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_SUBTASK_EXEC", sequenceName = "SQ_SUBTASK_EXEC", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SUBTASK_EXEC")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "TASK_EXEC_PKEY", nullable = false)
	public BigDecimal getTaskExecPkey() {
		return taskExecPkey;
	}

	@Column(name = "WORKORDER_ID", nullable = false)
	public String getWorkorderId() {
		return workorderId;
	}

	@Column(name = "CWS_ID", nullable = false)
	public Integer getCwsId() {
		return cwsId;
	}

	@Column(name = "SUBTASK_ID", nullable = false)
	public String getSubTaskId() {
		return subTaskId;
	}

	@Column(name = "SUBTASK_NM", nullable = false)
	public String getSubTaskName() {
		return subTaskName;
	}

	@Column(name = "SUBTASK_TYPE", nullable = false)
	public String getSubTaskType() {
		return subTaskType;
	}

	@Column(name = "SUBTASK_DESC", nullable = false)
	public String getSubTaskDesc() {
		return subTaskDesc;
	}

	@Column(name = "UI_BEAN", nullable = false)
	public String getUiBean() {
		return uiBean;
	}

	@Column(name = "END_POINT", nullable = false)
	public String getEndPoint() {
		return endPoint;
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

	@Column(name = "IS_OUT_OF_SLA", nullable = false)
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

	@Column(name = "SUBTASK_ASSIGNED_BY", nullable = false)
	public String getSubTaskAssignedBy() {
		return subTaskAssignedBy;
	}

	@Column(name = "SUBTASK_ASSIGNED_TIME", nullable = false)
	public Long getSubTaskAssignedTime() {
		return subTaskAssignedTime;
	}

	@Column(name = "SUBTASK_ASSIGNED_TO", nullable = false)
	public String getSubTaskAssignedTo() {
		return subTaskAssignedTo;
	}

	@Column(name = "HAS_PRODUCTIVITY", nullable = false)
	public boolean isProductivitySet() {
		return productivitySet;
	}

	@Column(name = "PRODUCTIVITY_HRS", nullable = false)
	public Integer getProductivityHrs() {
		return productivityHrs;
	}

	@Column(name = "IS_IGNORE_SLA", nullable = false)
	public boolean isIgnoreSla() {
		return ignoreSla;
	}

	@Column(name = "REGION_NM", nullable = false)
	public String getRegionName() {
		return regionName;
	}

	@Column(name = "MA_NM", nullable = false)
	public String getMaName() {
		return maName;
	}

	@Column(name = "GLID_NO", nullable = false)
	public Integer getGlid() {
		return glid;
	}

	@Column(name = "PSID_NO", nullable = false)
	public Integer getPsid() {
		return psid;
	}

	@Column(name = "SUBTASK_COMPLETED_TIME", nullable = false)
	public Long getSubTaskCompletedTime() {
		return subTaskCompletedTime;
	}

	@Column(name = "TASK_ID", nullable = false)
	public String getTaskId() {
		return taskId;
	}

	@Column(name = "TASK_NM", nullable = false)
	public String getTaskName() {
		return taskName;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setTaskExecPkey(BigDecimal taskExecPkey) {
		this.taskExecPkey = taskExecPkey;
	}

	public void setWorkorderId(String workorderId) {
		this.workorderId = workorderId;
	}

	public void setCwsId(Integer cwsId) {
		this.cwsId = cwsId;
	}

	public void setSubTaskId(String subTaskId) {
		this.subTaskId = subTaskId;
	}

	public void setSubTaskName(String subTaskName) {
		this.subTaskName = subTaskName;
	}

	public void setSubTaskType(String subTaskType) {
		this.subTaskType = subTaskType;
	}

	public void setSubTaskDesc(String subTaskDesc) {
		this.subTaskDesc = subTaskDesc;
	}

	public void setUiBean(String uiBean) {
		this.uiBean = uiBean;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
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

	public void setSubTaskAssignedBy(String subTaskAssignedBy) {
		this.subTaskAssignedBy = subTaskAssignedBy;
	}

	public void setSubTaskAssignedTime(Long subTaskAssignedTime) {
		this.subTaskAssignedTime = subTaskAssignedTime;
	}

	public void setSubTaskAssignedTo(String subTaskAssignedTo) {
		this.subTaskAssignedTo = subTaskAssignedTo;
	}

	public void setProductivitySet(boolean productivitySet) {
		this.productivitySet = productivitySet;
	}

	public void setProductivityHrs(Integer productivityHrs) {
		this.productivityHrs = productivityHrs;
	}

	public void setIgnoreSla(boolean ignoreSla) {
		this.ignoreSla = ignoreSla;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public void setMaName(String maName) {
		this.maName = maName;
	}

	public void setGlid(Integer glid) {
		this.glid = glid;
	}

	public void setPsid(Integer psid) {
		this.psid = psid;
	}

	public void setSubTaskCompletedTime(Long subTaskCompletedTime) {
		this.subTaskCompletedTime = subTaskCompletedTime;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
