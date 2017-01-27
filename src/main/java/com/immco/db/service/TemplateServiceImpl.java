package com.immco.db.service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immco.common.DBDC;
import com.immco.db.DbLogger;
import com.immco.db.dao.RoleTemplateAppFunMapDao;
import com.immco.db.dao.RoleTemplateDao;
import com.immco.db.model.router.role.AppUserRoleMap;
import com.immco.db.model.router.role.RoleTemplate;
import com.immco.db.model.router.role.RoleTemplateAppFunMap;

@Service("templateService")
@Transactional
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	private RoleTemplateAppFunMapDao roleTemplateAppFunMapDao;
	@Autowired
	private RoleTemplateDao roleTemplateDao;

	@Autowired
	private DbLogger dbLogger;

	@Override
	public DBDC createOrUpdateTemplate(RoleTemplate roleTemplate, List<RoleTemplateAppFunMap> roleTemplateAppFunMap) {
		DBDC dbdc = new DBDC();
		RoleTemplate primaryRoleTemplate = new RoleTemplate();
		roleTemplate.setRoleTemplateName(roleTemplate.getRoleTemplateName().toUpperCase().trim());
		if (roleTemplate.isPrimaryTemplate()) {
			List<RoleTemplate> findRoleTemplateByRole = roleTemplateDao.findRoleTemplateByRole(roleTemplate.getRolePkey());
			if (findRoleTemplateByRole.size() > 0) {
				findRoleTemplateByRole.forEach(e -> {
					if (e.isPrimaryTemplate()) {
						primaryRoleTemplate.setpKey(e.getpKey());
						primaryRoleTemplate.setPrimaryTemplate(false);
						primaryRoleTemplate.setRolePkey(e.getRolePkey());
						primaryRoleTemplate.setRoleTemplateName(e.getRoleTemplateName());
						primaryRoleTemplate.setCreatedDate(e.getCreatedDate());
						primaryRoleTemplate.setCreatedById(e.getCreatedById());
						primaryRoleTemplate.setCreatedByName(e.getCreatedByName());
						if (roleTemplate.getUpdatedDate() != null)
							primaryRoleTemplate.setUpdatedDate(roleTemplate.getUpdatedDate());
						else
							primaryRoleTemplate.setUpdatedDate(roleTemplate.getCreatedDate());

						if (roleTemplate.getUpdatedById() != null)
							primaryRoleTemplate.setUpdatedById(roleTemplate.getUpdatedById());
						else
							primaryRoleTemplate.setUpdatedById(roleTemplate.getCreatedById());

						if (roleTemplate.getUpdatedByName() != null)
							primaryRoleTemplate.setUpdatedByName(roleTemplate.getUpdatedByName());
						else
							primaryRoleTemplate.setUpdatedByName(roleTemplate.getCreatedByName());

					}
				});
			}

		}

		StatelessSession session = null;
		Transaction tx = null;
		try {
			session = roleTemplateDao.getStatelessSession();
			tx = session.beginTransaction();
			if (primaryRoleTemplate.getpKey() != null)
				roleTemplateDao.createOrUpdate(primaryRoleTemplate, session);
			roleTemplateDao.createOrUpdate(roleTemplate, session);
			final BigDecimal pKey = roleTemplate.getpKey();
			System.out.println(pKey);
			roleTemplateAppFunMap.forEach(e -> e.setRoleTemplatePkey(pKey));
			roleTemplateAppFunMapDao.createOrUpdate(roleTemplateAppFunMap, session);
			tx.commit();
			dbdc.setSuccess(true);
		} catch (Exception e) {
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
	public RoleTemplate findRoleTemplateById(BigDecimal pkey) {
		RoleTemplate roleTemplate = roleTemplateDao.findRoleTemplateById(pkey);
		return roleTemplate;
	}

	@Override
	public List<RoleTemplate> findRoleTemplateByRole(BigDecimal rolePkey) {
		List<RoleTemplate> roleTemplates = roleTemplateDao.findRoleTemplateByRole(rolePkey);
		return roleTemplates;
	}

	@Override
	public RoleTemplate findRoleTemplateByRoleAndName(String name, BigDecimal rolePkey) {
		RoleTemplate roleTemplate = roleTemplateDao.findRoleTemplateByRoleAndName(name, rolePkey);
		return roleTemplate;
	}

	@Override
	public List<RoleTemplateAppFunMap> findRoleTemplateAppFunMapByTemplateId(BigDecimal pkey) {
		List<RoleTemplateAppFunMap> roleTemplateAppFunMaps = roleTemplateAppFunMapDao.findRoleTemplateAppFunMapByTemplateId(pkey);
		return roleTemplateAppFunMaps;
	}

	@Override
	public DBDC deleteRoleTemplate(Number roleTemplatePkey) {
		DBDC dbdc = new DBDC();
		StatelessSession session = null;
		Transaction tx = null;
		try {
			session = roleTemplateDao.getStatelessSession();
			tx = session.beginTransaction();
			roleTemplateAppFunMapDao.deleteRoleTemplateAppFunMap(roleTemplatePkey, session);
			roleTemplateDao.deleteRoleTemplate(roleTemplatePkey, session);
			tx.commit();
			dbdc.setSuccess(true);
			dbdc.setSuccessMsg("Template Deleted");
		} catch (Exception e) {
			tx.rollback();
			dbLogger.logError(e, RoleTemplate.class, dbdc);
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return dbdc;
	}

	@Override
	public RoleTemplate findRoleTemplateByPrimaryRole(BigDecimal rolePkey) {
		RoleTemplate roleTemplates = roleTemplateDao.findRoleTemplateByPrimaryRole(rolePkey);
		return roleTemplates;
	}

	@Override
	public List<RoleTemplate> findPrimaryRoleTemplate() {
		List<RoleTemplate> roleTemplateAppFunMaps = roleTemplateDao.findPrimaryRoleTemplate();
		return roleTemplateAppFunMaps;
	}

	@Override
	public RoleTemplate findRoleTemplateByName(String name) {
		RoleTemplate roleTemplates = roleTemplateDao.findRoleTemplateByName(name);
		return roleTemplates;
	}

}
