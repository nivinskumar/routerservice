package com.immco.db.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.immco.db.model.router.role.AppUserRoleMap;

@Repository("appUserRoleMapDao")
public class AppUserRoleMapDaoImpl extends AbstractDao<BigDecimal, AppUserRoleMap> implements AppUserRoleMapDao {

	@Override
	public void createOrUpdateAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList) throws Exception {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			appUserRoleMapList.forEach(e -> session.saveOrUpdate(e));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public List<AppUserRoleMap> findAllAppUserRoleMapByUser(BigDecimal userPkey) throws Exception {
		Criterion crUserPkey = Restrictions.eq(AppUserRoleMap.USER_PKEY, userPkey);
		List<AppUserRoleMap> appUserRoleMapList = getSession().createCriteria(AppUserRoleMap.class).add(crUserPkey)
				.list();
		return appUserRoleMapList;
	}

	@Override
	public void deleteAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList) throws Exception {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			appUserRoleMapList.forEach(e -> session.delete(e));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<AppUserRoleMap> findAllAppUserRoleMapByUser(BigDecimal userPkey, StatelessSession session)
			throws Exception {
		Criterion crUserPkey = Restrictions.eq(AppUserRoleMap.USER_PKEY, userPkey);
		List<AppUserRoleMap> appUserRoleMapList = session.createCriteria(AppUserRoleMap.class).add(crUserPkey).list();
		return appUserRoleMapList;
	}

	@Override
	public void createOrUpdateAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList, StatelessSession session)
			throws Exception {
		appUserRoleMapList.forEach(e -> {

			if (e.getpKey() == null) {
				session.insert(e);

			} else {
				session.update(e);
			}

		});

	}

	@Override
	public void deleteAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList, StatelessSession session)
			throws Exception {
		appUserRoleMapList.forEach(e -> session.delete(e));

	}

	@Override
	public AppUserRoleMap findAppUserRoleMapByUserAndRole(BigDecimal userPkey, BigDecimal rolePkey) {
		Criterion crUserPkey = Restrictions.eq(AppUserRoleMap.USER_PKEY, userPkey);
		Criterion crRolePkey = Restrictions.eq(AppUserRoleMap.ROLE_PKEY, rolePkey);
		AppUserRoleMap appUserRoleMap =(AppUserRoleMap) getSession().createCriteria(AppUserRoleMap.class).add(Restrictions.and(crUserPkey,crRolePkey)).uniqueResult();
		return appUserRoleMap;
	}

}
