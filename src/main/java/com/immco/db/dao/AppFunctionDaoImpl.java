package com.immco.db.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.immco.db.model.router.role.AppFunction;

@Repository("templateDao")
public class AppFunctionDaoImpl extends AbstractDao<BigDecimal, AppFunction> implements AppFunctionDao {

	@Override
	public void createOrUpdateAppFunction(AppFunction appFunction) {
		getSession().saveOrUpdate(appFunction);
	}

}
