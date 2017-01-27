package com.immco.db.dao.exec;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.immco.db.dao.AbstractDao;
import com.immco.db.model.router.exec.WorkflowPhaseExec;
import com.immco.db.model.router.exec.SubTaskExec;
import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.exec.UDefCustomFormExec;
import com.immco.db.model.router.exec.UDefTaskExec;
import com.immco.db.model.router.exec.WorkFlowExec;

@Repository("workflowExecDao")
public class WorkFlowExecutorDaoImpl extends AbstractDao<BigDecimal, WorkFlowExec> implements WorkFlowExecutorDao {

	@Override
	public void createOrUpdateWorkFlow(WorkFlowExec workFlowExec) throws Exception {
		getSession().saveOrUpdate(workFlowExec);

	}

	@Override
	public void createOrUpdateMilestone(WorkflowPhaseExec milestoneExec) throws Exception {
		getSession().saveOrUpdate(milestoneExec);

	}

	@Override
	public void createOrUpdateTask(TaskExec taskExec) throws Exception {
		getSession().saveOrUpdate(taskExec);

	}

	@Override
	public void createOrUpdateSubTask(SubTaskExec subtaskExec) throws Exception {
		getSession().saveOrUpdate(subtaskExec);
	}
	
	public Session getASession(){
		 Session session = getSession();
		 return session;
	}

	@Override
	public void createOrUpdateUdefTaskExec(UDefTaskExec udefTaskExec) throws Exception {
		getSession().saveOrUpdate(udefTaskExec);
	}

	@Override
	public void createTaskExecutionEntry(TaskExec firstTask) {
		
		String sql="INSERT INTO TASK_ASSIGNMENT  (PKEY, CWS_ID, JOB_NM, STREET_ADDRESS, RGN_NM, MA_NM, GLID_NO, PSID_NO, WORK_GROUP_PKEY, ORG_LEVEL, ROLE_PKEY, QUEUE_NM, TASK_SUBTASK_FLG, TASK_ID, TASK_DESC," 
												+"ASSIGNED_TO_USER_ID, ASSIGNED_TO_USER_NM, TASK_STATUS)  Values (SQ_TASK_ASSIGNMENT.nextval,";
		sql+=firstTask.getCwsId()+",";
		sql+="(SELECT SURVEY_NM FROM SURVEY.SURVEY WHERE CWS_ID="+firstTask.getCwsId()+"),";
		sql+="(SELECT STREET_ADDRESS FROM SURVEY.SURVEY WHERE CWS_ID="+firstTask.getCwsId()+"),";
		sql+="'"+firstTask.getRgnNm()+"',";
		sql+="'"+firstTask.getMaNm()+"',";
		sql+=firstTask.getGlid()+",";
		sql+=firstTask.getPsid()+",";
		sql+=firstTask.getWorkGroupPkey()+",";
		sql+=firstTask.getOrgLevel()+",";
		sql+=firstTask.getRolePkey()+",";
//		sql+="'"+firstTask.getQueueName()+"',";
		sql+="'DESKTOP',";
		sql+="0,";
		sql+="'"+firstTask.getTaskId()+"',";
		sql+="'"+firstTask.getTaskDesc()+"',";
		sql+=null+",";
		sql+=null+",";
		sql+="1)";
		
		getSession().createSQLQuery(sql).executeUpdate();
		
	}
	
	
}