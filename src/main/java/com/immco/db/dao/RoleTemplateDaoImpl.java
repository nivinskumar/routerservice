package com.immco.db.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.immco.db.model.router.role.RoleTemplate;

@Repository("roleTemplateDao")
public class RoleTemplateDaoImpl extends AbstractDao<BigDecimal, RoleTemplate> implements RoleTemplateDao {

	@Override
	public void createOrUpdate(RoleTemplate roleTemplate, StatelessSession session) throws Exception {

		if (roleTemplate.getpKey() == null) {
			session.insert(roleTemplate);
		} else {
			session.update(roleTemplate);
		}

	}

	@Override
	public RoleTemplate findRoleTemplateById(BigDecimal pkey) {
		Criterion crPkey = Restrictions.eq(RoleTemplate.PKEY, pkey);
		RoleTemplate roleTemplate = (RoleTemplate) getSession().createCriteria(RoleTemplate.class).add(crPkey)
				.uniqueResult();
		return roleTemplate;
	}

	@Override
	public List<RoleTemplate> findRoleTemplateByRole(BigDecimal rolePkey) {
		Criterion crRolePkey = Restrictions.eq(RoleTemplate.ROLE_PKEY, rolePkey);
		List<RoleTemplate> roleTemplate = (List<RoleTemplate>) getSession().createCriteria(RoleTemplate.class)
				.add(crRolePkey).list();
		return roleTemplate;
	}

	@Override
	public RoleTemplate findRoleTemplateByRoleAndName(String name, BigDecimal rolePkey) {
		Criterion crRolePkey = Restrictions.eq(RoleTemplate.ROLE_PKEY, rolePkey);
		Criterion crName = Restrictions.eq(RoleTemplate.ROLE_TEMPLATE_NAME, name);
		RoleTemplate roleTemplate = (RoleTemplate) getSession().createCriteria(RoleTemplate.class)
				.add(Restrictions.and(crRolePkey, crName)).uniqueResult();
		return roleTemplate;
	}

	@Override
	public void deleteRoleTemplate(Number roleTemplatePkey, StatelessSession session) throws Exception {
		String deleteSQL = "DELETE FROM ROLETEMPLATE WHERE PKEY=" + roleTemplatePkey.longValue();
		session.createSQLQuery(deleteSQL).executeUpdate();

	}

	@Override
	public RoleTemplate findRoleTemplateByPrimaryRole(BigDecimal rolePkey) {
		Criterion crRolePkey = Restrictions.eq(RoleTemplate.ROLE_PKEY, rolePkey);
		Criterion crPrimary = Restrictions.eq(RoleTemplate.PRIMARY_TEMPLATE, true);
		RoleTemplate roleTemplate = (RoleTemplate) getSession().createCriteria(RoleTemplate.class)
				.add(Restrictions.and(crPrimary,crRolePkey)).uniqueResult();
		return roleTemplate;
	}

	@Override
	public List<RoleTemplate> findPrimaryRoleTemplate() {
		Criterion crPrimary = Restrictions.eq(RoleTemplate.PRIMARY_TEMPLATE, true);
		List<RoleTemplate> roleTemplates = (List<RoleTemplate>) getSession().createCriteria(RoleTemplate.class)
				.add(crPrimary).list();
		return roleTemplates;
	}

	@Override
	public RoleTemplate findRoleTemplateByName(String name) {
		Criterion crName = Restrictions.eq(RoleTemplate.ROLE_TEMPLATE_NAME, name.toUpperCase());
		RoleTemplate roleTemplate = (RoleTemplate) getSession().createCriteria(RoleTemplate.class)
				.add(crName).uniqueResult();
		return roleTemplate;
	}

}
