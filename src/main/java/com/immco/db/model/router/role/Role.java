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
@Table(name = "role", schema = "router")
public class Role implements IPersistable {

	public static final String CTX_CREATE_ROLE = "CTX_CREATE_ROLE";
	public static final String CTX_CREATE_ROLETEMPLATE = "CTX_CREATE_ROLETEMPLATE";

	public static final String PKEY = "pkey";
	public static final String ROLENAME = "roleName";

	private BigDecimal pKey;
	private String roleName;
	private Integer roleUid;

	private Date crtDt;
	private Integer crtById;
	private String crtByName;
	private Date updtDt;
	private Integer updtById;
	private String updtByName;

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_ROLE", sequenceName = "SQ_ROLE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROLE")

	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "ROLE_NM", nullable = false)
	public String getRoleName() {
		return roleName;
	}

	@Column(name = "CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}

	@Column(name = "CRT_BY", nullable = false)
	public Integer getCrtById() {
		return crtById;
	}

	@Column(name = "CRT_BY_NM", nullable = false)
	public String getCrtByName() {
		return crtByName;
	}

	@Column(name = "UPD_DT", nullable = false)
	public Date getUpdtDt() {
		return updtDt;
	}

	@Column(name = "UPD_BY", nullable = false)
	public Integer getUpdtById() {
		return updtById;
	}

	@Column(name = "UPD_BY_NM", nullable = false)
	public String getUpdtByName() {
		return updtByName;
	}

	@Column(name = "ROLE_UID", nullable = false)
	public Integer getRoleUid() {
		return roleUid;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public void setUpdtDt(Date updtDt) {
		this.updtDt = updtDt;
	}

	public void setUpdtById(Integer updtById) {
		this.updtById = updtById;
	}

	public void setUpdtByName(String updtByName) {
		this.updtByName = updtByName;
	}

	public void setRoleUid(Integer roleUid) {
		this.roleUid = roleUid;
	}
}
