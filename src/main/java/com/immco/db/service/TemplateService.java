package com.immco.db.service;

import java.math.BigDecimal;
import java.util.List;

import com.immco.common.DBDC;
import com.immco.db.model.router.role.RoleTemplate;
import com.immco.db.model.router.role.RoleTemplateAppFunMap;

public interface TemplateService {


	DBDC createOrUpdateTemplate(RoleTemplate roleTemplate, List<RoleTemplateAppFunMap> roleTemplateAppFunMap);

	RoleTemplate findRoleTemplateById(BigDecimal pkey);

	List<RoleTemplate> findRoleTemplateByRole(BigDecimal rolePkey);

	RoleTemplate findRoleTemplateByRoleAndName(String name, BigDecimal rolePkey);

	List<RoleTemplateAppFunMap> findRoleTemplateAppFunMapByTemplateId(BigDecimal pkey);

	RoleTemplate findRoleTemplateByPrimaryRole(BigDecimal rolePkey);

	List<RoleTemplate> findPrimaryRoleTemplate();

	DBDC deleteRoleTemplate(Number roleTemplatePkey);

	RoleTemplate findRoleTemplateByName(String name);
	
}
