package com.immco.db.dao.router;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.immco.db.dao.AbstractDao;
import com.immco.db.model.router.exec.WorkFlowExec;
import com.immco.db.model.router.templates.WorkFlowTemplate;

@SuppressWarnings("unchecked")
@Repository("workflowTemplateDao")
public class WorkflowTemplateDaoImpl extends AbstractDao<BigDecimal, WorkFlowTemplate> implements WorkflowTemplateDao {

	@Override
	public synchronized void createWorkflowTemplate(WorkFlowTemplate workFlowTemplate) throws Exception {
		Session session = getSession();
		workFlowTemplate.setCrtDt(new Date());
		session.save(workFlowTemplate);
	}

	@Override
	public WorkFlowTemplate findWorkflowTemplateByPK(BigDecimal PK) {
		return getByKey(PK);
	}

	@Override
	public WorkFlowTemplate findWorkflowTemplateByName(String name, BigDecimal pKey) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(WorkFlowTemplate.class)
				.add(Restrictions.eq(WorkFlowTemplate.WORKFLOW_NAME, name));
		if (pKey != null)
			criteria.add(Restrictions.ne(WorkFlowTemplate.PKEY, pKey));
		WorkFlowTemplate uniqueResult = (WorkFlowTemplate) criteria.uniqueResult();
		return uniqueResult;
	}

	@Override
	public WorkFlowTemplate findWorkflowTemplateByID(String id, BigDecimal pKey) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(WorkFlowTemplate.class)
				.add(Restrictions.eq(WorkFlowTemplate.WORKFLOW_ID, id));
		if (pKey != null)
			criteria.add(Restrictions.ne(WorkFlowTemplate.PKEY, pKey));
		WorkFlowTemplate uniqueResult = (WorkFlowTemplate) criteria.uniqueResult();
		return uniqueResult;
	}

	@Override
	public void updateWorkflowTemplate(WorkFlowTemplate workFlowTemplate) throws Exception {
		getSession().update(workFlowTemplate);
	}

	@Override
	public List<WorkFlowTemplate> getAllWorkflowTemplates() {
		return getSession().createCriteria(WorkFlowTemplate.class).list();
	}

	@Override
	public void deleteWorkflow(BigDecimal pKey) throws Exception {
		String sql = "DELETE FROM WORKFLOW_TEMPLATE WHERE PKEY=" + pKey;
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int checkPhaseExists(BigDecimal workflowPkey) {
		String sql = "SELECT COUNT(WORKFLOW_TEMPLATE_PKEY) FROM WORKFLOW_PHASE WHERE WORKFLOW_TEMPLATE_PKEY="
				+ workflowPkey;
		BigDecimal uniqueResult = (BigDecimal) getSession().createSQLQuery(sql).uniqueResult();
		return uniqueResult.intValue();

	}

	@Override
	public int findWorkflowExec(BigDecimal workflowTemplatePkey) {
		String sql = "SELECT COUNT(WORKFLOW_TEMPLATE_PKEY) FROM WORKFLOW_EXEC WHERE WORKFLOW_TEMPLATE_PKEY="
				+ workflowTemplatePkey;
		BigDecimal uniqueResult = (BigDecimal) getSession().createSQLQuery(sql).uniqueResult();
		return uniqueResult.intValue();
	}

	@Override
	public WorkFlowTemplate checkWorflowWithConstructionType(BigDecimal consTypePkey, BigDecimal workflowPkey) {
		WorkFlowTemplate workFlowTemplate = (WorkFlowTemplate) getSession().createCriteria(WorkFlowTemplate.class)
				.add(Restrictions.eq(WorkFlowTemplate.CONSTRUCTION_TYPE_PKEY, consTypePkey))
				.add(Restrictions.neOrIsNotNull(WorkFlowTemplate.PKEY, workflowPkey)).uniqueResult();
		return workFlowTemplate;
	}
}
