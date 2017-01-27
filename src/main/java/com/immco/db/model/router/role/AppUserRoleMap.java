package com.immco.db.model.router.role;

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
@Table(name = "appuser_role_map", schema = "router")
public class AppUserRoleMap implements IPersistable {

	public static final String CTX_FINDBY_USERPKEY = "CTX_FINDBY_USERPKEY";
	public static final String CTX_FINDBY_USERPKEY_ROLEPKEY = "CTX_FINDBY_USERPKEY_ROLEPKEY";
	
	public static final String USER_PKEY = "userPkey";
	public static final String PKEY = "pKey";
	public static final String ROLE_PKEY = "rolePkey";
	public static final String ROLE_TEMPLATE_PKEY = "roleTemplatePkey";
	public static final String PRIMARY_ROLE = "primaryRole";

	private BigDecimal pKey;
	private BigDecimal rolePkey;
	private BigDecimal userPkey;
	private BigDecimal roleTemplatePkey;
	private boolean primaryRole =false;

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_APPUSER_ROLE_MAP", sequenceName = "SQ_APPUSER_ROLE_MAP", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_APPUSER_ROLE_MAP")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "ROLE_PKEY", nullable = false)
	public BigDecimal getRolePkey() {
		return rolePkey;
	}

	@Column(name = "USER_PKEY", nullable = false)
	public BigDecimal getUserPkey() {
		return userPkey;
	}

	@Column(name = "ROLETEMPLATE_PKEY", nullable = false)
	public BigDecimal getRoleTemplatePkey() {
		return roleTemplatePkey;
	}

	@Column(name = "IS_PRIMARY_ROLE", nullable = false)
	public boolean isPrimaryRole() {
		return primaryRole;
	}
	
	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setRolePkey(BigDecimal rolePkey) {
		this.rolePkey = rolePkey;
	}

	public void setUserPkey(BigDecimal userPkey) {
		this.userPkey = userPkey;
	}

	public void setRoleTemplatePkey(BigDecimal roleTemplatePkey) {
		this.roleTemplatePkey = roleTemplatePkey;
	}
	
	public void setPrimaryRole(boolean primaryRole) {
		this.primaryRole = primaryRole;
	}
}