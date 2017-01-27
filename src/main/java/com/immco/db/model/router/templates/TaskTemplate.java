package com.immco.db.model.router.templates;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.immco.db.model.IPersistable;

@Entity
@Table(name = "tasktemplate", schema = "router")
public class TaskTemplate implements IPersistable {
	
	public static final String CREATE_APPROVAL_TASKTEMPLATE = "CREATE_APPROVAL_TASKTEMPLATE";
	public static final String CREATE_CONDITION_TASKTEMPLATE = "CREATE_CONDITION_TASKTEMPLATE";
	public static final String CREATE_TASKTEMPLATE = "CREATE_TASKTEMPLATE";
	public static final String UPDATE_TASKTEMPLATE = "UPDATE_TASKTEMPLATE";
	public static final String ASSOCIATE_UDEF_FORMTEMPLATE = "ASSOCIATE_UDEF_FORMTEMPLATE";
	public static final String ASSOCIATE_UDEF_MILESTONES = "ASSOCIATE_UDEF_MILESTONES";

	public static final String LIST_OF_TASKPKEYS = "LIST_OF_TASKPKEYS";
	public static final String MILESTONE_PKEY = "MILESTONE_PKEY";

	public static final String PKEY = "pKey";
	public static final String TASK_NAME = "taskName";
	public static final String TASK_ID = "taskId";

	private BigDecimal pKey;
	private String taskName;
	private String uiBean;
	private String endPoint;
	private String taskId;
	private String taskDesc;
	private BigDecimal successorPkey;
	private BigDecimal predessorPkey;
	private Integer sla;
	private Integer slaMetrics;
	private Integer phaseId;
	private Integer queueId;
	private Integer seqNo;
	private Integer orgLevel;
	private Integer taskType;
	private Integer slaPickup;
	private BigDecimal milestonePkey;
	private boolean productivitySet;
	private boolean ignoreSla;
	private BigDecimal rolePkey;
	private BigDecimal workgroupPkey;
	private boolean isConditionalParent;
	private boolean isConditionalChild;
	private BigDecimal conditionalParentPkey;
	private String conditionalChildPkey;
	private String lblApprove;
	private String lblDisapprove;
	private String execTaskTrue;
	private String execTaskFalse;
	private String conditionalUIbean;
	private String conditionalTaskParams;
	private String lblHumanTaskSubmit;
	private String humantaskUIbean;
	private String systemTaskEndPoint;

	private SubTaskTemplate subTaskTemplate[] = new SubTaskTemplate[0];
	private UDefFormTemplate udefFormTemplate[] = new UDefFormTemplate[0];

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_TASKTEMPLATE", sequenceName = "SQ_TASKTEMPLATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TASKTEMPLATE")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "TASK_ID", nullable = false)
	public String getTaskId() {
		return taskId;
	}

	@Column(name = "TASK_DESC")
	public String getTaskDesc() {
		return taskDesc;
	}

	@Column(name = "TASK_NM", nullable = false)
	public String getTaskName() {
		return taskName;
	}

	@Column(name = "UI_BEAN")
	public String getUiBean() {
		return uiBean;
	}

	@Column(name = "END_POINT")
	public String getEndPoint() {
		return endPoint;
	}

	@Column(name = "SUCCESSOR_PKEY")
	public BigDecimal getSuccessorPkey() {
		return successorPkey;
	}

	@Column(name = "PREDECESSOR_PKEY")
	public BigDecimal getPredessorPkey() {
		return predessorPkey;
	}

	@Column(name = "SLA_EXEC")
	public Integer getSla() {
		return sla;
	}

	@Column(name = "SAL_METRICS")
	public Integer getSlaMetrics() {
		return slaMetrics;
	}

	@Column(name = "PHASE_ID", nullable = false)
	public Integer getPhaseId() {
		return phaseId;
	}

	@Column(name = "QUEUE_ID", nullable = false)
	public Integer getQueueId() {
		return queueId;
	}

	@Column(name = "SEQ_NO", nullable = false)
	public Integer getSeqNo() {
		return seqNo;
	}

	@Column(name = "ORG_LEVEL", nullable = true)
	public Integer getOrgLevel() {
		return orgLevel;
	}

	@Column(name = "ROLE_PKEY", nullable = true)
	public BigDecimal getRolePkey() {
		return rolePkey;
	}

	@Column(name = "TASK_TYPE", nullable = true)
	public Integer getTaskType() {
		return taskType;
	}

	@Column(name = "SLA_PICK_IN_MIN", nullable = true)
	public Integer getSlaPickup() {
		return slaPickup;
	}

	@Column(name = "MILESTONETML_PKEY", nullable = false)
	public BigDecimal getMilestonePkey() {
		return milestonePkey;
	}

	@Column(name = "IS_IGNORE_SLA", nullable = false)
	public boolean isIgnoreSla() {
		return ignoreSla;
	}

	@Column(name = "IS_PRODUCTIVITY_SET", nullable = false)
	public boolean isProductivitySet() {
		return productivitySet;
	}

	@Column(name = "WORK_GROUP_PKEY", nullable = false)
	public BigDecimal getWorkgroupPkey() {
		return workgroupPkey;
	}

	@Column(name = "IS_CONDITIONAL_PARENT", nullable = true)
	public boolean isConditionalParent() {
		return isConditionalParent;
	}

	@Column(name = "IS_CONDITIONAL_CHILD", nullable = true)
	public boolean isConditionalChild() {
		return isConditionalChild;
	}

	@Column(name = "CONDITIONAL_PARENT_PKEY", nullable = true)
	public BigDecimal getConditionalParentPkey() {
		return conditionalParentPkey;
	}

	@Column(name = "CONDITIONAL_CHILDEREN_PKEY", nullable = true)
	public String getConditionalChildPkey() {
		return conditionalChildPkey;
	}

	@Column(name = "LBL_APPROVE", nullable = true)
	public String getLblApprove() {
		return lblApprove;
	}

	@Column(name = "LBL_DISAPPROVE", nullable = true)
	public String getLblDisapprove() {
		return lblDisapprove;
	}

	@Column(name = "EXEC_TASK_TRUE", nullable = true)
	public String getExecTaskTrue() {
		return execTaskTrue;
	}

	@Column(name = "EXEC_TASK_FALSE", nullable = true)
	public String getExecTaskFalse() {
		return execTaskFalse;
	}

	@Column(name = "CONDITIONAL_UI_BEAN", nullable = true)
	public String getConditionalUIbean() {
		return conditionalUIbean;
	}

	@Column(name = "CONDITIONAL_TASK_PARAMS", nullable = true)
	public String getConditionalTaskParams() {
		return conditionalTaskParams;
	}

	@Column(name = "LBL_HUMAN_TASK_SUBMIT", nullable = true)
	public String getLblHumanTaskSubmit() {
		return lblHumanTaskSubmit;
	}

	@Column(name = "HUMAN_TASK_UI_BEAN", nullable = true)
	public String getHumantaskUIbean() {
		return humantaskUIbean;
	}

	@Column(name = "SYSTEM_TASK_ENDPOINT", nullable = true)
	public String getSystemTaskEndPoint() {
		return systemTaskEndPoint;
	}

	@Transient
	public SubTaskTemplate[] getSubTaskTemplate() {
		return subTaskTemplate;
	}

	@Transient
	public UDefFormTemplate[] getUdefFormTemplate() {
		return udefFormTemplate;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public void setSla(Integer sla) {
		this.sla = sla;
	}

	public void setSlaMetrics(Integer slaMetrics) {
		this.slaMetrics = slaMetrics;
	}

	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public void setRolePkey(BigDecimal rolePkey) {
		this.rolePkey = rolePkey;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public void setSlaPickup(Integer slaPickup) {
		this.slaPickup = slaPickup;
	}

	public void setMilestonePkey(BigDecimal milestonePkey) {
		this.milestonePkey = milestonePkey;
	}

	public void setSubTaskTemplate(SubTaskTemplate subTaskTemplate[]) {
		this.subTaskTemplate = subTaskTemplate;
	}

	public void setIgnoreSla(boolean ignoreSla) {
		this.ignoreSla = ignoreSla;
	}

	public void setProductivitySet(boolean productivitySet) {
		this.productivitySet = productivitySet;
	}

	public void setWorkgroupPkey(BigDecimal workgroupPkey) {
		this.workgroupPkey = workgroupPkey;
	}

	public void setUdefFormTemplate(UDefFormTemplate[] udefFormTemplate) {
		this.udefFormTemplate = udefFormTemplate;
	}

	public void setConditionalParent(boolean isConditoinalParent) {
		this.isConditionalParent = isConditoinalParent;
	}

	public void setConditionalChild(boolean isConditoinalChild) {
		this.isConditionalChild = isConditoinalChild;
	}

	public void setConditionalParentPkey(BigDecimal conditionalParentPkey) {
		this.conditionalParentPkey = conditionalParentPkey;
	}

	public void setConditionalChildPkey(String conditionalChildPkey) {
		this.conditionalChildPkey = conditionalChildPkey;
	}

	public void setLblApprove(String lblApprove) {
		this.lblApprove = lblApprove;
	}

	public void setLblDisapprove(String lblDisapprove) {
		this.lblDisapprove = lblDisapprove;
	}

	public void setExecTaskTrue(String execTaskTrue) {
		this.execTaskTrue = execTaskTrue;
	}

	public void setExecTaskFalse(String execTaskFalse) {
		this.execTaskFalse = execTaskFalse;
	}

	public void setConditionalUIbean(String conditionalUIbean) {
		this.conditionalUIbean = conditionalUIbean;
	}

	public void setConditionalTaskParams(String conditionalTaskParams) {
		this.conditionalTaskParams = conditionalTaskParams;
	}

	public void setLblHumanTaskSubmit(String lblHumanTaskSubmit) {
		this.lblHumanTaskSubmit = lblHumanTaskSubmit;
	}

	public void setHumantaskUIbean(String humantaskUIbean) {
		this.humantaskUIbean = humantaskUIbean;
	}

	public void setSystemTaskEndPoint(String systemTaskEndPoint) {
		this.systemTaskEndPoint = systemTaskEndPoint;
	}

}
