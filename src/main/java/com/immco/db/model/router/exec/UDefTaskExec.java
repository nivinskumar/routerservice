package com.immco.db.model.router.exec;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.immco.db.model.IPersistable;

@Entity
@Table(name = "udef_task_exec", schema = "router")
public class UDefTaskExec implements IPersistable {
	
	public static final String PKEY="pKey";

	private BigDecimal pKey;
	private Integer cwsId;
	private BigDecimal milestoneExecPkey;
	private BigDecimal taskExecPkey;
	private String udefTaskType;
	private String assignedTo;
	private String assignedBy;
	private Date assignedDtTime;
	private String completedDtTime;
	private String comments;
	private String udefTaskDesc;
	private BigDecimal rolePkey;
	private BigDecimal workGrpPkey;
	private Date crtDate;
	private String formName;
	private String formAttributes;
	private String formDescription;
	private boolean mandatory;
	private Integer formColCount;

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_UDEF_TASK_EXEC", sequenceName = "SQ_UDEF_TASK_EXEC", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UDEF_TASK_EXEC")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "CWS_ID", nullable = false)
	public Integer getCwsId() {
		return cwsId;
	}

	@Column(name = "WORKFLOW_PHASE_EXEC_PKEY", nullable = false)
	public BigDecimal getMilestoneExecPkey() {
		return milestoneExecPkey;
	}

	@Column(name = "TASK_EXEC_PKEY", nullable = false)
	public BigDecimal getTaskExecPkey() {
		return taskExecPkey;
	}

	@Column(name = "UDEF_TASK_TYPE", nullable = false)
	public String getUdefTaskType() {
		return udefTaskType;
	}

	@Column(name = "ASSIGNED_TO", nullable = true)
	public String getAssignedTo() {
		return assignedTo;
	}

	@Column(name = "ASSIGNED_BY", nullable = true)
	public String getAssignedBy() {
		return assignedBy;
	}

	@Column(name = "ASSIGNED_DTTIME", nullable = true)
	public Date getAssignedDtTime() {
		return assignedDtTime;
	}

	@Column(name = "COMPLETED_DTTIME", nullable = false)
	public String getCompletedDtTime() {
		return completedDtTime;
	}

	@Column(name = "COMMENTS", nullable = false)
	public String getComments() {
		return comments;
	}

	@Column(name = "UDEF_TASK_DESC", nullable = false)
	public String getUdefTaskDesc() {
		return udefTaskDesc;
	}

//	@Column(name = "UDEF_TASK_NM", nullable = false)
//	public String getUdefTaskName() {
//		return udefTaskName;
//	}
	
	@Column(name = "ROLE_PKEY", nullable = false)
	public BigDecimal getRolePkey() {
		return rolePkey;
	}

	@Column(name = "WORK_GROUP_PKEY", nullable = false)
	public BigDecimal getWorkGrpPkey() {
		return workGrpPkey;
	}
	@Column(name = "CREATED_DTTIME", nullable = false)
	public Date getCrtDate() {
		return crtDate;
	}
	
	@Column(name = "FORM_NM", nullable = false)
	public String getFormName() {
		return formName;
	}

	@Column(name = "FORM_ATTRIBUTE", nullable = false)
	public String getFormAttributes() {
		return formAttributes;
	}

	@Column(name = "FORM_DESC", nullable = false)
	public String getFormDescription() {
		return formDescription;
	}

	@Column(name = "IS_MANDATORY", nullable = false)
	public boolean isMandatory() {
		return mandatory;
	}

	@Column(name = "FORM_COL_COUNT", nullable = false)
	public Integer getFormColCount() {
		return formColCount;
	}

	

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setCwsId(Integer cwsId) {
		this.cwsId = cwsId;
	}

	public void setMilestoneExecPkey(BigDecimal milestoneExecPkey) {
		this.milestoneExecPkey = milestoneExecPkey;
	}

	public void setTaskExecPkey(BigDecimal taskExecPkey) {
		this.taskExecPkey = taskExecPkey;
	}

	public void setUdefTaskType(String udefTaskType) {
		this.udefTaskType = udefTaskType;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}

	public void setAssignedDtTime(Date assignedDtTime) {
		this.assignedDtTime = assignedDtTime;
	}

	public void setCompletedDtTime(String completedDtTime) {
		this.completedDtTime = completedDtTime;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setUdefTaskDesc(String udefTaskDesc) {
		this.udefTaskDesc = udefTaskDesc;
	}

	public void setRolePkey(BigDecimal rolePkey) {
		this.rolePkey = rolePkey;
	}

	public void setWorkGrpPkey(BigDecimal workGrpPkey) {
		this.workGrpPkey = workGrpPkey;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	
	public void setFormName(String formName) {
		this.formName = formName;
	}

	public void setFormAttributes(String formAttributes) {
		this.formAttributes = formAttributes;
	}

	public void setFormDescription(String formDescription) {
		this.formDescription = formDescription;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}


	public void setFormColCount(Integer formColCount) {
		this.formColCount = formColCount;
	}
}