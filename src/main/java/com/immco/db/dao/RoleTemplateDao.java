package com.immco.db.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.StatelessSession;

import com.immco.db.model.router.role.RoleTemplate;

public interface RoleTemplateDao {
	public void createOrUpdate(RoleTemplate roleTemplate,StatelessSession session)throws Exception;
	
	public StatelessSession getStatelessSession();
	
	public RoleTemplate findRoleTemplateById(BigDecimal pkey);

	public List<RoleTemplate> findRoleTemplateByRole(BigDecimal rolePkey);

	public RoleTemplate findRoleTemplateByRoleAndName(String name, BigDecimal rolePkey);

	public void deleteRoleTemplate(Number roleTemplatePkey, StatelessSession session) throws Exception	;
	

	public RoleTemplate findRoleTemplateByPrimaryRole(BigDecimal rolePkey);

	public List<RoleTemplate> findPrimaryRoleTemplate();

	public RoleTemplate findRoleTemplateByName(String name);
}

