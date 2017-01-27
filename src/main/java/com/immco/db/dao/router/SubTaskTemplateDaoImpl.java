package com.immco.db.dao.router;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.immco.db.dao.AbstractDao;
import com.immco.db.model.router.templates.SubTaskTemplate;
import com.immco.db.model.router.templates.TaskTemplate;

@Repository("subTaskTemplateDao")
@SuppressWarnings("unchecked")
public class SubTaskTemplateDaoImpl extends AbstractDao<BigDecimal, SubTaskTemplate> implements SubTaskTemplateDao {

	@Override
	public void createSubTaskTemplate(SubTaskTemplate subTaskTemplate) throws Exception {
		getSession().save(subTaskTemplate);
		if(subTaskTemplate.getPredessorPkey()!=null){
			SubTaskTemplate taskTemplateByPK = findSubTaskTemplateByPK(subTaskTemplate.getPredessorPkey());
			taskTemplateByPK.setSuccessorPkey(subTaskTemplate.getpKey());
			getSession().update(taskTemplateByPK);
			
		}
	}

	@Override
	public void updateSubTaskTemplate(SubTaskTemplate subTaskTemplate) throws Exception {
		getSession().update(subTaskTemplate);
	}

	@Override
	public SubTaskTemplate findSubTaskTemplateByPK(BigDecimal PK) {
		return getByKey(PK);
	}

	@Override
	public SubTaskTemplate findSubTaskTemplateByID(String id, BigDecimal PK) {
		Criteria criteria = getSession().createCriteria(SubTaskTemplate.class)
				.add(Restrictions.eq(SubTaskTemplate.SUBTASK_ID, id))
				.add(Restrictions.neOrIsNotNull(SubTaskTemplate.PKEY, PK));
		SubTaskTemplate uniqueResult = (SubTaskTemplate) criteria.uniqueResult();
		return uniqueResult;
	}

	@Override
	public SubTaskTemplate findSubTaskTemplateByName(String name, BigDecimal PK) {
		Criteria criteria = getSession().createCriteria(SubTaskTemplate.class)
				.add(Restrictions.eq(SubTaskTemplate.SUBTASK_NAME, name))
				.add(Restrictions.neOrIsNotNull(SubTaskTemplate.PKEY, PK));
		SubTaskTemplate uniqueResult = (SubTaskTemplate) criteria.uniqueResult();
		return uniqueResult;
	}

	@Override
	public List<SubTaskTemplate> findAllSubTaskTemplates() {
		return getSession().createCriteria(SubTaskTemplate.class).list();
	}

	@Override
	public void deleteSubTasktemplate(BigDecimal pKey) throws Exception {
		StatelessSession session = getStatelessSession();
		try{
		SubTaskTemplate templateByPK = findSubTaskTemplateByPK(pKey);
		BigDecimal taskKey=templateByPK.getTaskPkey();
		if(templateByPK.getSuccessorPkey()!=null){
			SubTaskTemplate sucessorTask = findSubTaskTemplateByPK(templateByPK.getSuccessorPkey());
			sucessorTask.setPredessorPkey(templateByPK.getPredessorPkey());
			sucessorTask.setSeqNo(sucessorTask.getSeqNo()-1);
			session.update(sucessorTask);
		}
		if(templateByPK.getPredessorPkey()!=null){
			SubTaskTemplate predecessorTask = findSubTaskTemplateByPK(templateByPK.getPredessorPkey());
			predecessorTask.setSuccessorPkey(templateByPK.getSuccessorPkey());
			session.update(predecessorTask);
		}
		int y= session.createSQLQuery("DELETE FROM SUBTASK_TEMPLATE WHERE PKEY = "+pKey).executeUpdate();
		String sql="MERGE INTO   SUBTASK_TEMPLATE X USING   ( SELECT   (LEVEL - 1) LVL, PKEY  FROM   SUBTASK_TEMPLATE "
	             +" WHERE   TASKTEMPLATE_PKEY = "+taskKey+" START WITH   PREDESSOR_PKEY IS NULL "
	             +" CONNECT BY   PRIOR SUCCESSOR_PKEY = PKEY) Y ON   (X.PKEY = Y.PKEY) WHEN MATCHED THEN "
	             +" UPDATE SET X.SEQ_NO = Y.LVL";
		session.createSQLQuery(sql).executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

}
