package com.immco.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.dao.RoleDao;
import com.immco.db.model.router.role.Role;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	DbLogger dbLogger;

	@Override
	public DBDC createRole(Role role) throws Exception {
		DBDC dbDc = new DBDC();
		try {
			roleDao.createRole(role);
			dbDc.setSuccess(true);
			dbDc.setSuccessMsg("Role Created");
		} catch (Exception e) {
			dbLogger.logError(e, RoleServiceImpl.class, dbDc);
		}
		return dbDc;
	}
}
