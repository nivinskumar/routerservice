package com.immco.db.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.immco.db.model.router.role.RoleTemplateAppFunMap;

@Repository("roleTemplateAppFunMapDao")
public class RoleTemplateAppFunMapDaoImpl extends AbstractDao<BigDecimal, RoleTemplateAppFunMap>
		implements RoleTemplateAppFunMapDao {

	@Override
	public void createOrUpdate(List<RoleTemplateAppFunMap> roleTemplateAppFunMap, StatelessSession session)
			throws Exception {

		roleTemplateAppFunMap.forEach(e -> {
			try {
				if (e.getpKey() == null) {
					session.insert(e);
				} else {
					session.update(e);
				}
			} catch (Exception x) {
				throw x;
			}

		});

	}

	@Override
	public List<RoleTemplateAppFunMap> findRoleTemplateAppFunMapByTemplateId(BigDecimal pkey) {
		Criterion crRoleTemplatePkey = Restrictions.eq(RoleTemplateAppFunMap.ROLE_TEMPLATE_PKEY, pkey);
		List<RoleTemplateAppFunMap> roleTemplateAppFunMaps = getSession().createCriteria(RoleTemplateAppFunMap.class)
				.add(crRoleTemplatePkey).list();
		return roleTemplateAppFunMaps;
	}

	@Override
	public void deleteRoleTemplateAppFunMap(Number roleTemplatePkey, StatelessSession session) throws Exception {
		String deleteSQL = "DELETE FROM ROLETEMPLATE_APPFUN_MAP WHERE ROLETEMPLATE_PKEY=" + roleTemplatePkey.longValue();
		session.createSQLQuery(deleteSQL).executeUpdate();
	}

	

}
