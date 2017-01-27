package com.immco.db.model.router.templates;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Vector;

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
@Table(name = "workflow_template", schema = "router")
public class WorkFlowTemplate implements IPersistable {

	public static final String CTX_FIND_WORKFLOWTEMPLATE_BY_PKEY="CTX_FIND_WORKFLOWTEMPLATE_BY_PKEY";
	public static final String CREATE_WORKFLOWTEMPLATE ="CREATE_WORKFLOWTEMPLATE";
	public static final String UPDATE_WORKFLOWTEMPLATE ="UPDATE_WORKFLOWTEMPLATE";
	
	public static final String PKEY="pKey";
	public static final String WORKFLOW_NAME="workflowName";
	public static final String WORKFLOW_ID="workflowId";
	public static final String CONSTRUCTION_TYPE_PKEY="constructionTypePkey";
	
	
    private BigDecimal pKey;
    private String workflowId;
    private BigDecimal constructionTypePkey;
    private String workflowName;
    private String workflowDesc;
    private Date crtDt;
    private Integer crtById;
	private String crtByName;
	private BigDecimal jobTypePkey;

	private WorkflowPhase[] milestoneTemplates=new WorkflowPhase[0];

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_WORKFLOW_TEMPLATE", sequenceName = "SQ_WORKFLOW_TEMPLATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_WORKFLOW_TEMPLATE")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "WORKFLOW_ID", nullable = false)
	public String getWorkflowId() {
		return workflowId;
	}

	@Column(name = "WORKFLOW_NM", nullable = false)
	public String getWorkflowName() {
		return workflowName;
	}

	@Column(name = "WORKFLOW_DESC", nullable = false)
	public String getWorkflowDesc() {
		return workflowDesc;
	}

	@Column(name = "CONSTRUCTION_TYPE_PKEY", nullable = false)
	public BigDecimal getConstructionTypePkey() {
		return constructionTypePkey;
	}

	@Column(name = "CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}

	@Column(name = "CRT_BY_ID", nullable = false)
	public Integer getCrtById() {
		return crtById;
	}

	@Column(name = "CRT_BY_NM", nullable = false)
	public String getCrtByName() {
		return crtByName;
	}
	@Column(name = "JOB_TYPE_PKEY", nullable = false)
	public BigDecimal getJobTypePkey() {
		return jobTypePkey;
	}


	@Transient
	public WorkflowPhase[] getMilestoneTemplates() {
		return milestoneTemplates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pKey == null) ? 0 : pKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkFlowTemplate other = (WorkFlowTemplate) obj;
		if (pKey == null) {
			if (other.pKey != null)
				return false;
		} else if (!pKey.equals(other.pKey))
			return false;
		return true;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public void setConstructionTypePkey(BigDecimal constructionTypePkey) {
		this.constructionTypePkey = constructionTypePkey;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public void setCrtById(Integer crtById) {
		this.crtById = crtById;
	}

	public void setCrtByName(String crtByName) {
		this.crtByName = crtByName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public void setWorkflowDesc(String workflowDesc) {
		this.workflowDesc = workflowDesc;
	}

	public void setMilestoneTemplates(WorkflowPhase[] milestoneTemplates) {
		this.milestoneTemplates = milestoneTemplates;
	}
	public void setJobTypePkey(BigDecimal jobTypePkey) {
		this.jobTypePkey = jobTypePkey;
	}

}