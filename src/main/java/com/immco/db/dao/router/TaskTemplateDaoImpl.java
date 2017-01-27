package com.immco.db.dao.router;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.immco.db.dao.AbstractDao;
import com.immco.db.model.router.templates.TaskTemplate;
import com.immco.db.model.router.templates.UDefFormTemplate;
import com.immco.db.model.router.templates.WorkflowPhase;

@Repository("taskTemplateDao")
@SuppressWarnings("unchecked")
public class TaskTemplateDaoImpl extends AbstractDao<BigDecimal, TaskTemplate> implements TaskTemplateDao {

	@Override
	public void createTaskTemplate(TaskTemplate taskTemplate) throws Exception {
		getSession().save(taskTemplate);
		if (taskTemplate.getPredessorPkey() != null) {
			TaskTemplate taskTemplateByPK = findTaskTemplateByPK(taskTemplate.getPredessorPkey());
			taskTemplateByPK.setSuccessorPkey(taskTemplate.getpKey());
		}
	}

	@Override
	public void updateTaskTemplate(TaskTemplate taskTemplate) throws Exception {
		getSession().update(taskTemplate);

	}

	@Override
	public TaskTemplate findTaskTemplateByPK(BigDecimal PK) {
		TaskTemplate taskTemplate = getByKey(PK);
		return taskTemplate;
	}

	@Override
	public TaskTemplate findTaskTemplateByID(String id, BigDecimal pkey) {
		Criteria criteria = getSession().createCriteria(TaskTemplate.class)
				.add(Restrictions.eq(TaskTemplate.TASK_ID, id))
				.add(Restrictions.neOrIsNotNull(TaskTemplate.PKEY, pkey));
		TaskTemplate uniqueResult = (TaskTemplate) criteria.uniqueResult();
		return uniqueResult;
	}

	@Override
	public int findTaskTemplateByName(String name, BigDecimal pkey, BigDecimal workflowPkey) {
//		Criteria criteria = getSession().createCriteria(TaskTemplate.class)
//				.add(Restrictions.eq(TaskTemplate.TASK_NAME, name))
//				.add(Restrictions.neOrIsNotNull(TaskTemplate.PKEY, pkey));
//		TaskTemplate uniqueResult = (TaskTemplate) criteria.uniqueResult();
		String sql="SELECT COUNT(*) FROM TASKTEMPLATE T LEFT JOIN WORKFLOW_PHASE WP ON T.MILESTONETML_PKEY = WP.PKEY WHERE WP.WORKFLOW_TEMPLATE_PKEY="+workflowPkey+" AND  TASK_NM='"+name+"'";
		if(pkey!=null)
			sql=sql+" AND T.PKEY NOT IN("+pkey+")";
		BigDecimal uniqueResult = (BigDecimal) getSession().createSQLQuery(sql).uniqueResult();
		return uniqueResult.intValue();
	}

	@Override
	public List<TaskTemplate> findAllTaskTemplates() {
		return getSession().createCriteria(TaskTemplate.class).list();
	}

	@Override
	public UDefFormTemplate findUdefFormTemplate(BigDecimal taskTemplatePkey) {
		UDefFormTemplate uniqueResult = (UDefFormTemplate) getSession().createCriteria(UDefFormTemplate.class)
				.add(Restrictions.eq(UDefFormTemplate.TASK_TEMPLATE_PKEY, taskTemplatePkey)).uniqueResult();
		return uniqueResult;
	}

	@Override
	public void createOrUpdateTemplate(UDefFormTemplate parseIPersistable) {
		getSession().saveOrUpdate(parseIPersistable);

	}

	@Override
	public void deleteTaskTemplate(BigDecimal pKey) throws Exception {
		StatelessSession session = getStatelessSession();
		Transaction transaction = session.beginTransaction();
		try {
			TaskTemplate templateByPK = findTaskTemplateByPK(pKey);
			BigDecimal milestonePkey = templateByPK.getMilestonePkey();
			if (templateByPK.getSuccessorPkey() != null) {
				TaskTemplate sucessorTask = findTaskTemplateByPK(templateByPK.getSuccessorPkey());
				sucessorTask.setPredessorPkey(templateByPK.getPredessorPkey());
				sucessorTask.setSeqNo(sucessorTask.getSeqNo() - 1);
				session.update(sucessorTask);
			}
			if (templateByPK.getPredessorPkey() != null) {
				TaskTemplate predecessorTask = findTaskTemplateByPK(templateByPK.getPredessorPkey());
				predecessorTask.setSuccessorPkey(templateByPK.getSuccessorPkey());
				session.update(predecessorTask);
			}
			int y = session.createSQLQuery("DELETE FROM TASKTEMPLATE WHERE PKEY = " + pKey).executeUpdate();

			String sql = " MERGE INTO   TASKTEMPLATE X USING   ( SELECT   (LEVEL-1) LVL, PKEY  FROM   TASKTEMPLATE "
					+ " WHERE   MILESTONETML_PKEY = " + milestonePkey + " START WITH   PREDECESSOR_PKEY IS NULL "
					+ " CONNECT BY   PRIOR SUCCESSOR_PKEY = PKEY order by LEVEL) Y ON   (X.PKEY = Y.PKEY) WHEN MATCHED THEN "
					+ " UPDATE SET X.SEQ_NO = Y.LVL ";

			int x = session.createSQLQuery(sql).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public UDefFormTemplate findUdefFormTemplateByPkey(BigDecimal pKey) {
		UDefFormTemplate formTemplate = (UDefFormTemplate) getSession().createCriteria(UDefFormTemplate.class)
				.add(Restrictions.eq(UDefFormTemplate.PKEY, pKey)).uniqueResult();
		return formTemplate;
	}

	@Override
	public void associateUdefForm(BigDecimal taskPkey, BigDecimal UdefPkey) throws Exception {
		String sql="INSERT INTO TASKTEMPLATE_UDEF_FORM_MAP (TASKTEMPLATE_PKEY,UDEF_FORM_TEMPLATE_PKEY) VALUES("+taskPkey+","+UdefPkey+")";
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int checkUdefFromAssociation(BigDecimal taskPkey) {
		String sql="SELECT COUNT(*) FROM TASKTEMPLATE_UDEF_FORM_MAP WHERE TASKTEMPLATE_PKEY="+taskPkey;
		BigDecimal object = (BigDecimal) getSession().createSQLQuery(sql).uniqueResult();
		return object.intValue();
	}

	@Override
	public int checkMilestoneAssociation(BigDecimal taskPkey) {
		String sql="SELECT COUNT(TASK_TEMPLATE_PKEY) FROM MILESTONE_TASK_MAP WHERE TASK_TEMPLATE_PKEY="+taskPkey;
		BigDecimal object = (BigDecimal) getSession().createSQLQuery(sql).uniqueResult();
		return object.intValue();
	}

}
