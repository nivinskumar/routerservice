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
@Table(name = "udef_form_template", schema = "router")
public class UDefFormTemplate implements IPersistable {
	
	public static final String CREATE_OR_UPDATE = "CREATE_OR_UPDATE";
	public static final String REMOVE_UDEFFORM_FROM_TASK = "REMOVE_UDEFFORM_FROM_TASK";
	public static final String DELETE_FORM_TEMPLATE = "DELETE_FORM_TEMPLATE";


	public static final String PKEY = "pKey";
	public static final String TASK_TEMPLATE_PKEY = "taskTemplatePkey";
	public static final String TASK_UDEFFORM_MAP_PKEY = "TASK_UDEFFORM_MAP_PKEY";
	public static final String TEMPLATE_NAME="templateName";

	private BigDecimal pKey;
	// private BigDecimal taskTemplatePkey;
	private String maPkey;
	private String templateName;
	private String formTitle;
	private String formDesc;
	private boolean active;
	private String formAttrs;
	// private String formName;
	private boolean mandatory;
	private boolean applyGlobally;
	private String jobTypeUIDs;
	private Integer formColCount;
	private String constructionTypePkeys;

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_UDEF_FORM_TEMPLATE", sequenceName = "SQ_UDEF_FORM_TEMPLATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UDEF_FORM_TEMPLATE")
	public BigDecimal getpKey() {
		return pKey;
	}

	// @Column(name = "TASK_TEMPLATE_PKEY", nullable = false)
	// public BigDecimal getTaskTemplatePkey() {
	// return taskTemplatePkey;
	// }

	// @Column(name = "MILESTONETML_PKEY", nullable = false)
	// public BigDecimal getMilestoneTmlPkey() {
	// return milestoneTmlPkey;
	// }

	@Column(name = "MA_PKEYS", nullable = false)
	public String getMaPkey() {
		return maPkey;
	}

	@Column(name = "TEMPLATE_NM", nullable = false)
	public String getTemplateName() {
		return templateName;
	}

	@Column(name = "FORM_TITLE", nullable = false)
	public String getFormTitle() {
		return formTitle;
	}

	@Column(name = "FORM_DESC")
	public String getFormDesc() {
		return formDesc;
	}

	@Column(name = "IS_ACTIVE", nullable = false)
	public boolean isActive() {
		return active;
	}

	@Column(name = "FORM_ATTRIBUTES", nullable = false)
	public String getFormAttrs() {
		return formAttrs;
	}

	// @Column(name = "FORM_NM", nullable = true)
	// public String getFormName() {
	// return formName;
	// }

	@Column(name = "IS_MANDATORY", nullable = false)
	public boolean isMandatory() {
		return mandatory;
	}

	@Column(name = "APPLY_GLOBALLY", nullable = false)
	public boolean isApplyGlobally() {
		return applyGlobally;
	}

	@Column(name = "JOB_TYPE_UIDS", nullable = false)
	public String getJobTypeUIDs() {
		return jobTypeUIDs;
	}

	@Column(name = "FORM_COL_COUNT", nullable = false)
	public Integer getFormColCount() {
		return formColCount;
	}

	@Column(name = "CONSTRUCTION_TYPE_PKEYS", nullable = true)
	public String getConstructionTypePkeys() {
		return constructionTypePkeys;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	// public void setTaskTemplatePkey(BigDecimal taskTemplatePkey) {
	// this.taskTemplatePkey = taskTemplatePkey;
	// }

	// public void setMilestoneTmlPkey(BigDecimal milestoneTmlPkey) {
	// this.milestoneTmlPkey = milestoneTmlPkey;
	// }

	public void setMaPkey(String maPkey) {
		this.maPkey = maPkey;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}

	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setFormAttrs(String formAttrs) {
		this.formAttrs = formAttrs;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public void setApplyGlobally(boolean applyGlobally) {
		this.applyGlobally = applyGlobally;
	}

	public void setJobTypeUIDs(String jobTypeUIDs) {
		this.jobTypeUIDs = jobTypeUIDs;
	}

	// public void setFormName(String formName) {
	// this.formName = formName;
	// }

	public void setFormColCount(Integer formColCount) {
		this.formColCount = formColCount;
	}

	public void setConstructionTypePkeys(String constructionTypePkeys) {
		this.constructionTypePkeys = constructionTypePkeys;
	}

}
