package com.immco.db.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.StatelessSession;

import com.immco.db.model.router.role.RoleTemplateAppFunMap;

public interface RoleTemplateAppFunMapDao {

	void createOrUpdate(List<RoleTemplateAppFunMap> roleTemplateAppFunMap, StatelessSession session) throws Exception;

	List<RoleTemplateAppFunMap> findRoleTemplateAppFunMapByTemplateId(BigDecimal pkey);

	void deleteRoleTemplateAppFunMap(Number roleTemplatePkey, StatelessSession session)throws Exception;
}
