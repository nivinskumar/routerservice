package com.immco.db.dao.exec;

import java.util.Map;

import org.hibernate.Session;

import com.immco.db.model.router.exec.WorkflowPhaseExec;
import com.immco.common.DBDC;
import com.immco.db.model.router.exec.SubTaskExec;
import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.exec.UDefCustomFormExec;
import com.immco.db.model.router.exec.UDefTaskExec;
import com.immco.db.model.router.exec.WorkFlowExec;

public interface WorkFlowExecutorDao {

	void createOrUpdateWorkFlow(WorkFlowExec workFlowExec) throws Exception;

	void createOrUpdateMilestone(WorkflowPhaseExec milestoneExec) throws Exception;

	void createOrUpdateTask(TaskExec taskExec) throws Exception;

	void createOrUpdateSubTask(SubTaskExec subtaskExec) throws Exception;
	
	void createOrUpdateUdefTaskExec(UDefTaskExec udefTaskExec) throws Exception;
	
	Session getASession();
	
	void createTaskExecutionEntry(TaskExec firstTask)  throws Exception;
}
