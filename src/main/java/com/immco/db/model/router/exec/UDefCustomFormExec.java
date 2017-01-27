package com.immco.db.model.router.exec;

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
@Table(name = "udef_custom_form_exec", schema = "router")
public class UDefCustomFormExec implements IPersistable {

	public static final String PKEY = "pKey";
	private BigDecimal pKey;
	private Integer cws_id;
	private BigDecimal UdefTaskExecPkey;
	private String formName;
	private String formAttributes;
	private String formDescription;
	private boolean mandatory;
	private Integer formColCount;
	

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_SUBTASK_EXEC", sequenceName = "SQ_SUBTASK_EXEC", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SUBTASK_EXEC")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "CWS_ID", nullable = false)
	public Integer getCws_id() {
		return cws_id;
	}

	@Column(name = "UDEF_TASK_EXEC_PKEY", nullable = false)
	public BigDecimal getUdefTaskExecPkey() {
		return UdefTaskExecPkey;
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

	public void setCws_id(Integer cws_id) {
		this.cws_id = cws_id;
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

	public void setUdefTaskExecPkey(BigDecimal udefTaskExecPkey) {
		UdefTaskExecPkey = udefTaskExecPkey;
	}

	public void setFormColCount(Integer formColCount) {
		this.formColCount = formColCount;
	}

}
