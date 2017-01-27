package com.immco.db.model.router.role;

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
@Table(name = "roletemplate", schema = "router")
public class RoleTemplate implements IPersistable {

	public static final String CTX_FIND_ROLETEMPLATE_BY_PK = "CTX_FIND_ROLETEMPLATE_BY_PK";
	public static final String CTX_FIND_ALL_ROLETEMPLATE_BY_ROLE = "CTX_FIND_ALL_ROLETEMPLATE_BY_ROLE";
	public static final String CTX_FIND_ROLETEMPLATE_BY_NAME_AND_ROLE = "CTX_FIND_ROLETEMPLATE_BY_NAME_AND_ROLE";
	public static final String CTX_FIND_ROLETEMPLATE_BY_NAME = "CTX_FIND_ROLETEMPLATE_BY_NAME";
	public static final String CTX_FIND_ROLETEMPLATE_BY_PRIMARYROLE = "CTX_FIND_ROLETEMPLATE_BY_PRIMARYROLE";
	public static final String CTX_FIND_ALL_ROLETEMPLATE_BY_PRIMARYROLE = "CTX_FIND_ALL_ROLETEMPLATE_BY_PRIMARYROLE";

	public static final String PKEY = "pKey";
	public static final String ROLE_PKEY = "rolePkey";
	public static final String ROLE_TEMPLATE_NAME = "roleTemplateName";
	public static final String PRIMARY_TEMPLATE = "primaryTemplate";
	public static final String CREATED_DATE = "createdDate";
	public static final String CREATED_BY_ID = "createdById";
	public static final String CREATED_BY_NAME = "createdByName";
	public static final String UPDATED_DATE = "updatedDate";
	public static final String UPDATED_BY_ID = "updatedById";
	public static final String UPDATED_BY_NAME = "updatedByName";

	private BigDecimal pKey;
	private String roleTemplateName;
	private BigDecimal rolePkey;
	private boolean primaryTemplate = false;
	private Date createdDate;
	private BigDecimal createdById;
	private String createdByName;
	private Date updatedDate;
	private BigDecimal updatedById;
	private String updatedByName;

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_ROLETEMPLATE", sequenceName = "SQ_ROLETEMPLATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROLETEMPLATE")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "ROLETEMPLATE_NM", nullable = false)
	public String getRoleTemplateName() {
		return roleTemplateName;
	}

	@Column(name = "ROLE_PKEY", nullable = false)
	public BigDecimal getRolePkey() {
		return rolePkey;
	}

	@Column(name = "IS_PRIMARY_TEMPLATE", nullable = false)
	public boolean isPrimaryTemplate() {
		return primaryTemplate;
	}

	@Column(name = "CRT_DT", nullable = true)
	public Date getCreatedDate() {
		return createdDate;
	}

	@Column(name = "CRT_BY_ID", nullable = true)
	public BigDecimal getCreatedById() {
		return createdById;
	}

	@Column(name = "CRT_BY_NM", nullable = true)
	public String getCreatedByName() {
		return createdByName;
	}

	@Column(name = "UPD_DT", nullable = true)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	@Column(name = "UPD_BY_ID", nullable = true)
	public BigDecimal getUpdatedById() {
		return updatedById;
	}

	@Column(name = "UPD_BY_NM", nullable = true)
	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setRoleTemplateName(String roleTemplateName) {
		this.roleTemplateName = roleTemplateName;
	}

	public void setRolePkey(BigDecimal rolePkey) {
		this.rolePkey = rolePkey;
	}

	public void setPrimaryTemplate(boolean primaryTemplate) {
		this.primaryTemplate = primaryTemplate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setCreatedById(BigDecimal createdById) {
		this.createdById = createdById;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setUpdatedById(BigDecimal updatedById) {
		this.updatedById = updatedById;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
}
