package com.immco.db.dao;

import com.immco.db.model.router.role.AppFunction;

public interface AppFunctionDao {
	void createOrUpdateAppFunction(AppFunction appFunction);
}
