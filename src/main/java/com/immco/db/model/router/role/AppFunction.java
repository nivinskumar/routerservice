package com.immco.db.model.router.role;

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
@Table(name = "app_function", schema = "router")
public class AppFunction implements IPersistable {

	private BigDecimal pKey;
	private String functionName;
	private String functionOid;
	private BigDecimal parentPkey;
	private Integer displayOrder;
	private Integer phaseId = -1;
	private Integer queueId = -1;

	private AppFunction parent;

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_APP_FUNCTION", sequenceName = "SQ_APP_FUNCTION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_APP_FUNCTION")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "FUNCTION_NM", nullable = false)
	public String getFunctionName() {
		return functionName;
	}

	@Column(name = "FUNCTION_OID", nullable = false)
	public String getFunctionOid() {
		return functionOid;
	}

	@Column(name = "PARENT_PKEY", nullable = false)
	public BigDecimal getParentPkey() {
		return parentPkey;
	}

	@Column(name = "DISP_ORDER", nullable = false)
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	@Column(name = "PHASE_ID", nullable = false)
	public Integer getPhaseId() {
		return phaseId;
	}

	@Column(name = "QUEUE_ID", nullable = false)
	public Integer getQueueId() {
		return queueId;
	}

	@Transient
	public AppFunction getParent() {
		return parent;
	}

	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public void setFunctionOid(String functionOid) {
		this.functionOid = functionOid;
	}

	public void setParentPkey(BigDecimal parentPkey) {
		this.parentPkey = parentPkey;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public void setParent(AppFunction parent) {
		this.parent = parent;
	}

}
