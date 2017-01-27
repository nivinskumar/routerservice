package com.immco.db.dao.router;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.immco.db.dao.AbstractDao;
import com.immco.db.model.router.templates.Milestones;
import com.immco.db.model.router.templates.WorkflowPhase;

@SuppressWarnings("unchecked")
@Repository("milestoneTemplateDao")
public class MilestoneTemplateDaoImpl extends AbstractDao<BigDecimal, WorkflowPhase>
		implements MilestoneTemplateDao {

	@Override
	public void createMilestoneTemplate(WorkflowPhase milestoneTemplate) {
		getSession().save(milestoneTemplate);
	}

	@Override
	public void updateMilestoneTemplate(WorkflowPhase milestoneTemplate) {
		getSession().saveOrUpdate(milestoneTemplate);
	}

	@Override
	public WorkflowPhase findMilestoneTemplateByID(String Id, BigDecimal pKey) {
		Criteria criteria = getSession().createCriteria(WorkflowPhase.class)
				.add(Restrictions.eq(WorkflowPhase.WORKFLOWPHASE_ID, Id))
				.add(Restrictions.neOrIsNotNull(WorkflowPhase.PKEY, pKey));
		WorkflowPhase uniqueResult = (WorkflowPhase) criteria.uniqueResult();
		return uniqueResult;
	}

	@Override
	public WorkflowPhase findMilestoneTemplateByName(String name, BigDecimal pKey) {
		Criteria criteria = getSession().createCriteria(WorkflowPhase.class)
				.add(Restrictions.eq(WorkflowPhase.WORKFLOWPHASE_NM, name))
				.add(Restrictions.neOrIsNotNull(WorkflowPhase.PKEY, pKey));
		WorkflowPhase uniqueResult = (WorkflowPhase) criteria.uniqueResult();
		return uniqueResult;
	}

	@Override
	public WorkflowPhase findMilestoneTemplateByPK(BigDecimal pKey) {
		WorkflowPhase milestoneTemplate = getByKey(pKey);
		return milestoneTemplate;
	}

	@Override
	public List<WorkflowPhase> getAllMilestones() {
		return getSession().createCriteria(WorkflowPhase.class).list();
	}

	@Override
	public void deleteMilestoneTemplate(Number pkey,Number workflowPkey) throws Exception {
		StatelessSession statelessSession = getStatelessSession();
		try{
			String sql="DELETE FROM WORKFLOW_PHASE WHERE PKEY="+pkey;
			statelessSession.createSQLQuery(sql).executeUpdate();
			String sqlUpdate="MERGE INTO   WORKFLOW_PHASE X USING   (SELECT ROWNUM RN ,PKEY  FROM (SELECT  PKEY FROM   WORKFLOW_PHASE"
					+ " WHERE    WORKFLOW_TEMPLATE_PKEY ="+workflowPkey+" ORDER BY PKEY ) ) Y ON   (X.PKEY = Y.PKEY) WHEN MATCHED THEN UPDATE SET X.SEQ_NO = Y.RN-1";
			statelessSession.createSQLQuery(sqlUpdate).executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			statelessSession.close();
		}
		
	}

	@Override
	public void associateTaskAndMilestones(BigDecimal milestonePkey, List<Integer> taskPkeyList) {
		for(Integer taskPkey:taskPkeyList){
			String sql="INSERT INTO MILESTONE_TASK_MAP  (MILESTONE_PKEY,TASK_TEMPLATE_PKEY) VALUES("+milestonePkey+","+taskPkey+")";
			getSession().createSQLQuery(sql).executeUpdate();
		}
	}

	@Override
	public void createOrUpdateMilestones(Milestones milestones) throws Exception {
		getSession().saveOrUpdate(milestones);
	}

	@Override
	public Milestones findMilestonesByPkey(BigDecimal pKey) {
		Criteria criteria=getSession().createCriteria(Milestones.class).add(Restrictions.eq(Milestones.PKEY, pKey));
		Milestones uniqueResult = (Milestones) criteria.uniqueResult();
		return uniqueResult;
	}

	@Override
	public Milestones findMilestonesByName(BigDecimal pKey, String Name) {
		Criteria criteria=getSession().createCriteria(Milestones.class).add(Restrictions.eq(Milestones.MILESTONE_NM, Name))
				.add(Restrictions.neOrIsNotNull(Milestones.PKEY, pKey));
		Milestones milestones=(Milestones) criteria.uniqueResult();
		return milestones;
	}

	@Override
	public Milestones findMilestonesById(BigDecimal pKey, String Id) {
		Criteria criteria=getSession().createCriteria(Milestones.class).add(Restrictions.eq(Milestones.MILESTONE_ID, Id))
				.add(Restrictions.neOrIsNotNull(Milestones.PKEY, pKey));
		Milestones milestones=(Milestones) criteria.uniqueResult();
		return milestones;
	}

	@Override
	public void deleteMilestones(BigDecimal pKey) {
		String sql="DELETE FROM MILESTONES WHERE PKEY="+pKey;
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int findMilestoneAssociated(BigDecimal pKey) {
		String sql="SELECT COUNT(MILESTONE_PKEY) FROM ROUTER.MILESTONE_TASK_MAP WHERE MILESTONE_PKEY="+pKey;
		BigDecimal count= (BigDecimal) getSession().createSQLQuery(sql).uniqueResult();
		return count.intValue();
	}

}
