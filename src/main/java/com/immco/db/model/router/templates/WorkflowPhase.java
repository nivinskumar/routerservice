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
@Table(name = "workflow_phase", schema = "router")
public class WorkflowPhase implements IPersistable {

	public static final String CREATE_WORKFLOWPHASE= "CREATE_WORKFLOWPHASE";
	public static final String UPDATE_WORKFLOWPHASE = "UPDATE_WORKFLOWPHASE";

	public static final int SLA_METRICS_MINUTES = 0;
	public static final int SLA_METRICS_HOURS = 1;
	public static final int SLA_METRICS_DAYS = 2;

	public static final String PKEY = "pKey";
	public static final String WORKFLOWPHASE_NM = "workflowPhaseName";
	public static final String WORKFLOWPHASE_ID = "workflowPhaseId";
	public static final String WORKFLOW_PKEY="workflowTemplatePkey";

	private BigDecimal pKey;
	private String workflowPhaseId;
	private String workflowPhaseDesc;
	private String workflowPhaseName;
	private Integer sla;
	private Integer slaMetrics;
	private BigDecimal workflowTemplatePkey;
	private Integer seqNo;

	private TaskTemplate[] taskTemplates = new TaskTemplate[0];

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_WORKFLOW_PHASE", sequenceName = "SQ_WORKFLOW_PHASE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_WORKFLOW_PHASE")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "WORKFLOW_PHASE_NM", nullable = false)
	public String getWorkflowPhaseName() {
		return workflowPhaseName;
	}

	@Column(name = "WORKFLOW_PHASE_ID", nullable = false)
	public String getWorkflowPhaseId() {
		return workflowPhaseId;
	}

	@Column(name = "WORKFLOW_PHASE_DESC", nullable = false)
	public String getWorkflowPhaseDesc() {
		return workflowPhaseDesc;
	}

	// @Column(name = "MILESTONE_COMMENT", nullable = false)
	// public String getMilestoneComment() {
	// return milestoneComment;
	// }

	@Column(name = "SLA", nullable = false)
	public Integer getSla() {
		return sla;
	}

	@Column(name = "SLA_METRICS", nullable = false)
	public Integer getSlaMetrics() {
		return slaMetrics;
	}

	@Column(name = "WORKFLOW_TEMPLATE_PKEY", nullable = false)
	public BigDecimal getWorkflowTemplatePkey() {
		return workflowTemplatePkey;
	}

	@Column(name = "SEQ_NO", nullable = false)
	public Integer getSeqNo() {
		return seqNo;
	}

	@Transient
	public TaskTemplate[] getTaskTemplates() {
		return taskTemplates;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setWorkflowPhaseName(String phaseName) {
		this.workflowPhaseName = phaseName;
	}

	public void setSla(Integer sla) {
		this.sla = sla;
	}

	public void setSlaMetrics(Integer slaMetrics) {
		this.slaMetrics = slaMetrics;
	}

	public void setWorkflowTemplatePkey(BigDecimal workflowTemplatePkey) {
		this.workflowTemplatePkey = workflowTemplatePkey;
	}

	public void setWorkflowPhaseId(String phaseId) {
		this.workflowPhaseId = phaseId;
	}

	public void setWorkflowPhaseDesc(String phaseDesc) {
		this.workflowPhaseDesc = phaseDesc;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public void setTaskTemplates(TaskTemplate[] taskTemplates) {
		this.taskTemplates = taskTemplates;
	}
}