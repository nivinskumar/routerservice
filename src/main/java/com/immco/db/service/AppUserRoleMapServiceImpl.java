package com.immco.db.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.dao.AppUserRoleMapDao;
import com.immco.db.model.router.role.AppUserRoleMap;



@Service("appUserRoleMapService")
@Transactional
public class AppUserRoleMapServiceImpl implements AppUserRoleMapService {

	@Autowired
	private AppUserRoleMapDao appUserRoleMapDao;

	@Autowired
	private DbLogger dbLogger;

	@Override
	public DBDC createOrUpdateAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList) {

		DBDC dbDc = new DBDC();
		try {
			appUserRoleMapDao.createOrUpdateAppUserRoleMap(appUserRoleMapList);
			dbDc.setSuccess(true);
			dbDc.setSuccessMsg("User Role Created");
		} catch (Exception e) {
			dbLogger.logError(e, AppUserRoleMapServiceImpl.class, dbDc);
		}
		return dbDc;
	}

	@Override
	public List<AppUserRoleMap> findAllAppUserRoleMapByUser(BigDecimal userPkey) {
		DBDC dbDc = new DBDC();
		try {
			return appUserRoleMapDao.findAllAppUserRoleMapByUser(userPkey);
		} catch (Exception e) {
			dbLogger.logError(e, AppUserRoleMapServiceImpl.class, dbDc);
		}
		return null;
	}

	@Override
	public DBDC deleteAppUserRoleMap(List<AppUserRoleMap> appUserRoleMapList) {
		DBDC dbDc = new DBDC();
		try {
			appUserRoleMapDao.deleteAppUserRoleMap(appUserRoleMapList);
			dbDc.setSuccess(true);
			dbDc.setSuccessMsg("User Role deleted");
		} catch (Exception e) {
			dbLogger.logError(e, AppUserRoleMapServiceImpl.class, dbDc);
		}
		return dbDc;
	}

	@Override
	public DBDC createOrUpdateOrDeleteAppUserRoleMap(final List<AppUserRoleMap> appUserRoleMapList, BigDecimal userPkey) {

		DBDC dbdc = new DBDC();
		StatelessSession session = null;
		Transaction tx = null;
		try {
			session = appUserRoleMapDao.getStatelessSession();
			tx = session.beginTransaction();

			List<AppUserRoleMap> findAllAppUserRoleMapByUser = appUserRoleMapDao.findAllAppUserRoleMapByUser(userPkey,
					session);

			List<AppUserRoleMap> deleteList = new ArrayList<AppUserRoleMap>();
			
			findAllAppUserRoleMapByUser.forEach(e -> {
				
				for(AppUserRoleMap m: appUserRoleMapList)
				{
					if(m.getRolePkey().longValue()==e.getRolePkey().intValue() && m.getRoleTemplatePkey().longValue()== e.getRoleTemplatePkey().longValue())
					{
						m.setpKey(e.getpKey());
					}
				}
			});

			findAllAppUserRoleMapByUser.forEach(e -> {
				Optional<AppUserRoleMap> findFirst = appUserRoleMapList.stream()						
						.filter(s -> (s.getpKey()==null?0:s.getpKey().longValue()) == e.getpKey().longValue()).findFirst();
				if (!findFirst.isPresent() ) {
					deleteList.add(e);
				}
			});

			List<AppUserRoleMap> collect = appUserRoleMapList.stream().filter(e -> e.getpKey()==null).collect(Collectors.toList());
			
			appUserRoleMapDao.createOrUpdateAppUserRoleMap(collect, session);

			if (!deleteList.isEmpty()) {
				appUserRoleMapDao.deleteAppUserRoleMap(deleteList, session);

			}

			dbdc.setSuccessMsg("Role Saved");
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			dbLogger.logError(e, AppUserRoleMap.class, dbdc);
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return dbdc;

	}

	@Override
	public AppUserRoleMap findAppUserRoleMapByUserAndRole(BigDecimal userPkey, BigDecimal rolePkey) {
		DBDC dbDc = new DBDC();
		try {
			return appUserRoleMapDao.findAppUserRoleMapByUserAndRole(userPkey,rolePkey);
		} catch (Exception e) {
			dbLogger.logError(e, AppUserRoleMapServiceImpl.class, dbDc);
		}
		return null;
	}

}
