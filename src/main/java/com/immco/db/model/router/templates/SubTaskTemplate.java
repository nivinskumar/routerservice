package com.immco.db.model.router.templates;

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
@Table(name = "subtask_template", schema = "router")
public class SubTaskTemplate implements IPersistable {

	public static final String CREATE_SUBTASKTEMPLATE = "CREATE_SUBTASKTEMPLATE";
	public static final String UPDATE_SUBTASKTEMPLATE = "UPDATE_SUBTASKTEMPLATE";

	public static final String PKEY = "pKey";
	public static final String SUBTASK_NAME = "subTaskNm";
	public static final String SUBTASK_ID = "subTaskId";

	private BigDecimal pKey;
	private String subTaskId;
	private String subTaskNm;
	private String subTaskDesc;
	private Integer taskType;
	private Integer seqNo;
	private String uiBean;
	private String endPoint;
	private BigDecimal successorPkey;
	private BigDecimal predessorPkey;
	private Integer slaExec;
	private Integer slaMetrics;
	private boolean concurrent;
	private Integer phaseId;
	private Integer queueId;
	private BigDecimal taskPkey;
	private boolean ignoreSla;
	private boolean productivitySet;
	private BigDecimal rolePkey;
	private BigDecimal workgroupPkey;

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_SUBTASK_TEMPLATE", sequenceName = "SQ_SUBTASK_TEMPLATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SUBTASK_TEMPLATE")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "SUBTASK_ID", nullable = false)
	public String getSubTaskId() {
		return subTaskId;
	}

	@Column(name = "SUBTASK_NM", nullable = false)
	public String getSubTaskNm() {
		return subTaskNm;
	}

	@Column(name = "SUBTASK_DESC")
	public String getSubTaskDesc() {
		return subTaskDesc;
	}

	@Column(name = "SUBTASK_TYPE", nullable = false)
	public Integer getTaskType() {
		return taskType;
	}

	@Column(name = "UI_BEAN")
	public String getUiBean() {
		return uiBean;
	}

	@Column(name = "END_POINT", nullable = true)
	public String getEndPoint() {
		return endPoint;
	}

	@Column(name = "SUCCESSOR_PKEY", nullable = true)
	public BigDecimal getSuccessorPkey() {
		return successorPkey;
	}

	@Column(name = "PREDESSOR_PKEY", nullable = true)
	public BigDecimal getPredessorPkey() {
		return predessorPkey;
	}

	@Column(name = "SLA_EXEC", nullable = true)
	public Integer getSlaExec() {
		return slaExec;
	}

	@Column(name = "SLA_METRICS")
	public Integer getSlaMetrics() {
		return slaMetrics;
	}

	@Column(name = "IS_CONCURRENT")
	public boolean isConcurrent() {
		return concurrent;
	}

	@Column(name = "PHASE_ID", nullable = true)
	public Integer getPhaseId() {
		return phaseId;
	}

	@Column(name = "QUEUE_ID", nullable = true)
	public Integer getQueueId() {
		return queueId;
	}

	@Column(name = "SEQ_NO", nullable = true)
	public Integer getSeqNo() {
		return seqNo;
	}

	@Column(name = "TASKTEMPLATE_PKEY  ", nullable = false)
	public BigDecimal getTaskPkey() {
		return taskPkey;
	}

	@Column(name = "IS_IGNORE_SLA", nullable = false)
	public boolean isIgnoreSla() {
		return ignoreSla;
	}

	@Column(name = "HAS_PRODUCTIVITY", nullable = false)
	public boolean isProductivitySet() {
		return productivitySet;
	}
	
	@Column(name = "ROLE_PKEY", nullable = true)
	public BigDecimal getRolePkey() {
		return rolePkey;
	}
	
	@Column(name = "WORK_GROUP_PKEY", nullable = false)
	public BigDecimal getWorkgroupPkey() {
		return workgroupPkey;
	}

	public void setpKey(BigDecimal pkey) {
		this.pKey = pkey;
	}

	public void setSubTaskId(String subTaskId) {
		this.subTaskId = subTaskId;
	}

	public void setSubTaskNm(String subTaskNm) {
		this.subTaskNm = subTaskNm;
	}

	public void setSubTaskDesc(String subTaskDesc) {
		this.subTaskDesc = subTaskDesc;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public void setUiBean(String uiBean) {
		this.uiBean = uiBean;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public void setSuccessorPkey(BigDecimal successorPkey) {
		this.successorPkey = successorPkey;
	}

	public void setPredessorPkey(BigDecimal predessorPkey) {
		this.predessorPkey = predessorPkey;
	}

	public void setSlaExec(Integer slaExec) {
		this.slaExec = slaExec;
	}

	public void setSlaMetrics(Integer slaMetrics) {
		this.slaMetrics = slaMetrics;
	}

	public void setConcurrent(boolean concurrent) {
		this.concurrent = concurrent;
	}

	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public void setTaskPkey(BigDecimal taskPkey) {
		this.taskPkey = taskPkey;
	}

	public void setIgnoreSla(boolean ignoreSla) {
		this.ignoreSla = ignoreSla;
	}

	public void setProductivitySet(boolean productivitySet) {
		this.productivitySet = productivitySet;
	}
	
	public void setRolePkey(BigDecimal rolePkey) {
		this.rolePkey = rolePkey;
	}

	public void setWorkgroupPkey(BigDecimal workgroupPkey) {
		this.workgroupPkey = workgroupPkey;
	}
}