package com.immco.db.service;

import java.math.BigDecimal;
import java.util.List;

import com.immco.common.DBDC;
import com.immco.db.model.router.role.AppUserRoleMap;

public interface AppUserRoleMapService {


	DBDC createOrUpdateAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList);

	List<AppUserRoleMap> findAllAppUserRoleMapByUser(BigDecimal userPkey);
	
	DBDC deleteAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList);	

	DBDC createOrUpdateOrDeleteAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList, BigDecimal userPkey);

	AppUserRoleMap findAppUserRoleMapByUserAndRole(BigDecimal userPkey, BigDecimal rolePkey);
	
}
