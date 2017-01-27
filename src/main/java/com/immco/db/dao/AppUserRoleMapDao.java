package com.immco.db.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.StatelessSession;

import com.immco.db.model.router.role.AppUserRoleMap;

public interface AppUserRoleMapDao {

	void createOrUpdateAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList) throws Exception;

	List<AppUserRoleMap> findAllAppUserRoleMapByUser(BigDecimal userPkey) throws Exception;

	void deleteAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList) throws Exception;

	StatelessSession getStatelessSession() throws Exception;

	List<AppUserRoleMap> findAllAppUserRoleMapByUser(BigDecimal userPkey, StatelessSession session) throws Exception;

	void createOrUpdateAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList, StatelessSession session)
			throws Exception;

	void deleteAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList, StatelessSession session) throws Exception;

	AppUserRoleMap findAppUserRoleMapByUserAndRole(BigDecimal userPkey, BigDecimal rolePkey);

}
