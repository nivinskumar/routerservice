package com.immco.db.dao.exec;

import java.math.BigDecimal;

import com.immco.db.model.router.exec.UDefCustomFormExec;
import com.immco.db.model.router.exec.UDefTaskExec;

public interface UdefCustomFormExecDao {
	
	public void createOrUpdateUdefCustomFormExec(UDefTaskExec taskExec) throws Exception;
	
	UDefTaskExec findUDefCustomFormExecByPK(BigDecimal pKey);

}
