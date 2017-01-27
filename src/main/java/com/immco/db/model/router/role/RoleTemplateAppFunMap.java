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
@Table(name = "roletemplate_appfun_map", schema = "router")
public class RoleTemplateAppFunMap implements IPersistable {
	
	
	public static final String ROLE_TEMPLATE_PKEY ="roleTemplatePkey";
	public static final String APP_FUNCTION_PKEY ="appFunctionPkey";

	private BigDecimal pKey;
	private BigDecimal roleTemplatePkey;
	private BigDecimal appFunctionPkey;
	private Integer read =new Integer(0);
	private Integer write=new Integer(0);
	private Integer execute=new Integer(0);
	private Integer hide=new Integer(0);

	@Column(name = "PKEY", unique = true, nullable = false)
	@Id
	@SequenceGenerator(name = "SQ_ROLETEMPLATE_APPFUN_MAP", sequenceName = "SQ_ROLETEMPLATE_APPFUN_MAP", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROLETEMPLATE_APPFUN_MAP")
	public BigDecimal getpKey() {
		return pKey;
	}

	@Column(name = "ROLETEMPLATE_PKEY", nullable = false)
	public BigDecimal getRoleTemplatePkey() {
		return roleTemplatePkey;
	}

	@Column(name = "APPFUNCTION_PKEY", nullable = false)
	public BigDecimal getAppFunctionPkey() {
		return appFunctionPkey;
	}

	@Column(name = "READ", nullable = false)
	public Integer getRead() {
		return read;
	}

	@Column(name = "WRITE", nullable = false)
	public Integer getWrite() {
		return write;
	}

	@Column(name = "EXECUTE", nullable = false)
	public Integer getExecute() {
		return execute;
	}

	@Column(name = "HIDE", nullable = false)
	public Integer getHide() {
		return hide;
	}

	public void setpKey(BigDecimal pKey) {
		this.pKey = pKey;
	}

	public void setRoleTemplatePkey(BigDecimal roleTemplatePkey) {
		this.roleTemplatePkey = roleTemplatePkey;
	}

	public void setAppFunctionPkey(BigDecimal appFunctionPkey) {
		this.appFunctionPkey = appFunctionPkey;
	}

	public void setRead(Integer read) {
		this.read = read;
	}

	public void setWrite(Integer write) {
		this.write = write;
	}

	public void setExecute(Integer execute) {
		this.execute = execute;
	}

	public void setHide(Integer hide) {
		this.hide = hide;
	}
}
