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
@Table(name = "workflow_exec", schema = "router")
public class WorkFlowExec implements IPersistable {
	
	public static final String WORKFLOW_TEMPLATE_PKEY="workFlowTemplatePkey";

	private BigDecimal pKey;
	private Integer cwsId;
	private BigDecimal workFlowTemplatePkey;
	private String workFlowName;
	private String workFlowId;
	private String workFlowDesc;
	private String constructionType;
	private String workOrderId;
	private Date crtDt;
	private Date completedDt;
	private String execOutcome;

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_WORKFLOW_EXEC", sequenceName = "SQ_WORKFLOW_EXEC", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_WORKFLOW_EXEC")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "CWS_ID", nullable = false)
	public Integer getCwsId() {
		return cwsId;
	}

	@Column(name = "WORKFLOW_TEMPLATE_PKEY", nullable = false)
	public BigDecimal getWorkFlowTemplatePkey() {
		return workFlowTemplatePkey;
	}

	@Column(name = "WORKFLOW_NM", nullable = false)
	public String getWorkFlowName() {
		return workFlowName;
	}

	@Column(name = "WORKFLOW_ID", nullable = false)
	public String getWorkFlowId() {
		return workFlowId;
	}

	@Column(name = "WORKFLOW_DESC")
	public String getWorkFlowDesc() {
		return workFlowDesc;
	}

	@Column(name = "CONSTRUCTION_TYPE_NM", nullable = false)
	public String getConstructionType() {
		return constructionType;
	}

	@Column(name = "WORKORDER_ID", nullable = false)
	public String getWorkOrderId() {
		return workOrderId;
	}

	@Column(name = "CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}

	@Column(name = "COMPLETED_DT", nullable = false)
	public Date getCompletedDt() {
		return completedDt;
	}

	@Column(name = "EXEC_OUTCOME", nullable = false)
	public String getExecOutcome() {
		return execOutcome;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setCwsId(Integer cwsId) {
		this.cwsId = cwsId;
	}

	public void setWorkFlowTemplatePkey(BigDecimal workFlowTemplatePkey) {
		this.workFlowTemplatePkey = workFlowTemplatePkey;
	}

	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}

	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}

	public void setWorkFlowDesc(String workFlowDesc) {
		this.workFlowDesc = workFlowDesc;
	}

	public void setConstructionType(String constructionType) {
		this.constructionType = constructionType;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public void setCompletedDt(Date completedDt) {
		this.completedDt = completedDt;
	}

	public void setExecOutcome(String execOutcome) {
		this.execOutcome = execOutcome;
	}

}
