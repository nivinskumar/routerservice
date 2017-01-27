package com.immco.db.service;

import com.immco.common.DBDC;
import com.immco.db.model.router.role.Role;

public interface RoleService {

	DBDC createRole(Role role) throws Exception;
}
