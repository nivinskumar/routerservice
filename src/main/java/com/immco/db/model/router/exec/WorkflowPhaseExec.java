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

import com.immco.db.model.IPersistable;


@Entity
@Table(name = "workflow_phase_exec", schema = "router")
public class WorkflowPhaseExec implements IPersistable{

    private BigDecimal pKey;
    private Integer cwsId;
    private BigDecimal workflowExecPkey;
    private String workorderId;
    private String workflowPhaseId;
    private String workflowPhaseName;
    private String workflowPhaseDesc;
    private Date workflowPhaseStartDate;
    private Date workflowPhaseCompDate;
    private String workflowPhaseStatus;
    private Date workflowPhaseLandedDate;
    private String assignedToUserId;

    @Column(name = "PKEY", unique = true, nullable = false)
    @Id
    @SequenceGenerator(name = "SQ_WORKFLOW_PHASE_EXEC", sequenceName = "SQ_WORKFLOW_PHASE_EXEC", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_WORKFLOW_PHASE_EXEC")
    public BigDecimal getpKey() {
	return pKey;
    }
    
    @Column(name = "CWS_ID", nullable = false)
    public Integer getCwsId() {
	return cwsId;
    }

    @Column(name = "WORKFLOW_EXEC_PKEY", nullable = false)
    public BigDecimal getWorkflowExecPkey() {
	return workflowExecPkey;
    }

    @Column(name = "WORKORDER_ID", nullable = false)
    public String getWorkorderId() {
	return workorderId;
    }

    @Column(name = "WORKFLOW_PHASE_NM", nullable = false)
    public String getWorkflowPhaseName() {
	return workflowPhaseName;
    }

    @Column(name = "WORKFLOW_PHASE_DESC")
    public String getWorkflowPhaseDesc() {
	return workflowPhaseDesc;
    }

    @Column(name = "WORKFLOW_PHASE_START_DT")
    public Date getWorkflowPhaseStartDate() {
	return workflowPhaseStartDate;
    }

    @Column(name = "WORKFLOW_PHASE_COMP_DT")
    public Date getWorkflowPhaseCompDate() {
	return workflowPhaseCompDate;
    }

    @Column(name = "WORKFLOW_PHASE_STATUS")
    public String getWorkflowPhaseStatus() {
	return workflowPhaseStatus;
    }
    
    @Column(name = "WORKFLOW_PHASE_ID")
    public String getWorkflowPhaseId() {
		return workflowPhaseId;
	}
    
    @Column(name = "WORKFLOW_PHASE_LANDED_DT")
    public Date getWorkflowPhaseLandedDate() {
		return workflowPhaseLandedDate;
	}
	
    @Column(name = "ASSIGNED_TO")
	public String getAssignedToUserId() {
		return assignedToUserId;
	}

    public void setpKey(BigDecimal pKey) {
	this.pKey = pKey;
    }

    public void setCwsId(Integer cwsId) {
	this.cwsId = cwsId;
    }

    public void setWorkflowExecPkey(BigDecimal workflowExecPkey) {
	this.workflowExecPkey = workflowExecPkey;
    }

    public void setWorkorderId(String workorderId) {
	this.workorderId = workorderId;
    }

    public void setWorkflowPhaseName(String workflowPhaseName) {
	this.workflowPhaseName = workflowPhaseName;
    }

    public void setWorkflowPhaseDesc(String workflowPhaseDesc) {
	this.workflowPhaseDesc = workflowPhaseDesc;
    }

    public void setWorkflowPhaseStartDate(Date workflowPhaseStartDate) {
	this.workflowPhaseStartDate = workflowPhaseStartDate;
    }

    public void setWorkflowPhaseCompDate(Date workflowPhaseCompDate) {
	this.workflowPhaseCompDate = workflowPhaseCompDate;
    }

    public void setWorkflowPhaseStatus(String workflowPhaseStatus) {
	this.workflowPhaseStatus = workflowPhaseStatus;
    }

	public void setWorkflowPhaseId(String workflowPhaseId) {
		this.workflowPhaseId = workflowPhaseId;
	}

	public void setWorkflowPhaseLandedDate(Date workflowPhaseLandedDate) {
		this.workflowPhaseLandedDate = workflowPhaseLandedDate;
	}

	public void setAssignedToUserId(String assignedToUserId) {
		this.assignedToUserId = assignedToUserId;
	}
}
