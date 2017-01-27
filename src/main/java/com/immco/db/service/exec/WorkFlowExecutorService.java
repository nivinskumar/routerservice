package com.immco.db.service.exec;

import java.math.BigDecimal;

import com.immco.common.DBDC;
import com.immco.db.model.router.exec.TaskExec;

public interface WorkFlowExecutorService {

	DBDC createWorkFlowExec(BigDecimal conctructionTypePkey, Integer cwsId, Integer phase);
	
	DBDC createTaskExecutionEntry(TaskExec firstTask)  throws Exception;
	
}