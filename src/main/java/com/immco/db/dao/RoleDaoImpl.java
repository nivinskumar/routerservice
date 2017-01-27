package com.immco.db.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.immco.db.model.router.role.Role;


@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<BigDecimal, Role> implements RoleDao {

	@Override
	public void createRole(Role role) throws Exception {
		getSession().save(role);
	}

}
