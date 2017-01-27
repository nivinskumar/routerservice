package com.immco.db.service.exec;

import com.immco.common.DBDC;
import com.immco.db.model.router.exec.UDefCustomFormExec;
import com.immco.db.model.router.exec.UDefTaskExec;
import com.immco.db.proxy.DCParam;

public interface UdefCustomFormExecService {
	
	DBDC createOrUpdateCustomForm(UDefTaskExec taskExec) throws Exception;
	
	UDefTaskExec findUdefCustomFormExec(DCParam dcParam);
}
