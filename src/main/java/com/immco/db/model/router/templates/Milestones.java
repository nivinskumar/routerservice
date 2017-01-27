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
@Table(name = "milestones", schema = "router")
public class Milestones implements IPersistable {
	
	public static final String CREATE_OR_UPDATE_MILESTONE="CREATE_OR_UPDATE_MILESTONE";
	public static final String FIND_MILESTONE_BY_PKEY="FIND_MILESTONE_BY_PKEY";
	public static final String DELETE_MILESTONE="DELETE_MILESTONE";
	
	public static final String PKEY="pKey";
	public static final String MILESTONE_ID="milestoneId";
	public static final String MILESTONE_NM="milestoneNm";
	
	private BigDecimal pKey;
	private String milestoneId;
	private String milestoneNm;

	
	
	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_MILESTONES", sequenceName = "SQ_MILESTONES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MILESTONES")
	public BigDecimal getpKey() {
		return pKey;
	}
	@Column(name = "MILESTONE_ID", nullable = false)
	public String getMilestoneId() {
		return milestoneId;
	}
	@Column(name = "MILESTONE_NM", nullable = false)
	public String getMilestoneNm() {
		return milestoneNm;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setMilestoneId(String milestoneId) {
		this.milestoneId = milestoneId;
	}

	public void setMilestoneNm(String milestoneNm) {
		this.milestoneNm = milestoneNm;
	}

}
