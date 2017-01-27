package com.immco.db.dao.router;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.immco.db.dao.AbstractDao;
import com.immco.db.model.router.exec.TaskExec;
import com.immco.db.model.router.templates.UDefFormTemplate;

@Repository("udefFormTemplateDao")
public class UdefFormTemplateDaoImpl extends AbstractDao<BigDecimal, UDefFormTemplate> implements UdefFormTemplateDao {

	@Override
	public void createOrUpdateTemplate(UDefFormTemplate parseIPersistable) {
		getSession().saveOrUpdate(parseIPersistable);

	}

	@Override
	public UDefFormTemplate findUdefFormTemplateByPkey(BigDecimal pKey) {
		UDefFormTemplate formTemplate = getByKey(pKey);
		return formTemplate;
	}

	@Override
	public void deleteUdefFormTemplate(BigDecimal pKey) throws Exception {
		String sql = "DELETE FROM UDEF_FORM_TEMPLATE WHERE PKEY=" + pKey;
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int findAssociatedToTask(BigDecimal pKey) {
		String sql = "SELECT COUNT(*) FROM TASKTEMPLATE_UDEF_FORM_MAP WHERE UDEF_FORM_TEMPLATE_PKEY=" + pKey;
		BigDecimal object = new BigDecimal(getSession().createSQLQuery(sql).uniqueResult().toString());
		return object.intValue();
	}

	@Override
	public BigDecimal removeUdefFormTaskAssociation(BigDecimal pKey) throws Exception {
		String sqlUdefPkey="SELECT UDEF_FORM_TEMPLATE_PKEY FROM TASKTEMPLATE_UDEF_FORM_MAP WHERE PKEY=" + pKey;
		BigDecimal udefPkey= (BigDecimal) getSession().createSQLQuery(sqlUdefPkey).uniqueResult();
		String sql = "DELETE FROM TASKTEMPLATE_UDEF_FORM_MAP WHERE PKEY=" + pKey;
		getSession().createSQLQuery(sql).executeUpdate();
		return udefPkey;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UDefFormTemplate> getAllUdefFormTemplates() {
		return getSession().createCriteria(UDefFormTemplate.class).list();
	}

	@Override
	public BigDecimal findTaskByUdefFormPkey(BigDecimal pKey) {
		String sql = "SELECT TASKTEMPLATE_PKEY FROM TASKTEMPLATE_UDEF_FORM_MAP WHERE UDEF_FORM_TEMPLATE_PKEY=" + pKey;
		BigDecimal taskPkey = (BigDecimal) getSession().createSQLQuery(sql).uniqueResult();
		return taskPkey;
	}

	@Override
	public TaskExec findFirstTaskExec(BigDecimal cwsId) {
		String sql="SELECT MIN(PKEY) FROM ROUTER.TASK_EXEC WHERE CWS_ID="+cwsId;
		BigDecimal pkey = (BigDecimal) getSession().createSQLQuery(sql).uniqueResult();
		TaskExec taskExec = (TaskExec) getSession().createCriteria(TaskExec.class)
				.add(Restrictions.eq(TaskExec.PKEY, pkey))
				.uniqueResult();
		return taskExec;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskExec> findNextTaskExec(BigDecimal cwsId,String task_id) {
		
		List<TaskExec> taskExec = (List<TaskExec>) getSession().createCriteria(TaskExec.class)
				.add(Restrictions.eq(TaskExec.PRED_TASK_ID, task_id))
				.add(Restrictions.eq(TaskExec.CWS_ID, cwsId.intValue()))
				.list();
		return taskExec;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskExec> findPreviousTaskExec(BigDecimal cwsId,String task_id) {
		
		List<TaskExec> taskExec = (List<TaskExec>) getSession().createCriteria(TaskExec.class)
				.add(Restrictions.eq(TaskExec.SUCC_TASK_ID, task_id))
				.add(Restrictions.eq(TaskExec.CWS_ID, cwsId.intValue()))
				.list();
		return taskExec;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskExec> findCurrentTaskExec(BigDecimal cwsId) {
		List<TaskExec> taskExec = (List<TaskExec>) getSession().createCriteria(TaskExec.class)
				.add(Restrictions.isNotNull(TaskExec.TASK_PICKUP_TIME))
				.add(Restrictions.isNull(TaskExec.TASK_COMPLETED_TIME))
				.add(Restrictions.eq(TaskExec.CWS_ID, cwsId.intValue()))
				.list();
		return taskExec;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskExec> findLastTaskExec(BigDecimal cwsId) {
		List<TaskExec> taskExec = (List<TaskExec>) getSession().createCriteria(TaskExec.class)
				.add(Restrictions.isNull(TaskExec.SUCC_TASK_ID))
				.add(Restrictions.eq(TaskExec.CWS_ID, cwsId.intValue()))
				.list();
		return taskExec;
	}
	
	
}
