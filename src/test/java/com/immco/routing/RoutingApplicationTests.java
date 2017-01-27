package com.immco.routing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.immco.db.model.router.role.AppUserRoleMap;
import com.immco.db.service.AppUserRoleMapService;
import com.immco.db.service.RoleService;
import com.immco.db.service.TemplateService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RoutingApplication.class)
@WebAppConfiguration
public class RoutingApplicationTests
{
	@Autowired private RoleService roleService;
	
	
	@Autowired private TemplateService templateService;
	
	@Autowired private AppUserRoleMapService appUserRoleMapService;
	
	
//	@Test
//	public void testCreateRold(){
//		Role role = new Role();
//		role.setCrtById(2);
//		role.setCrtByName("Mike");
//		role.setCrtDt(new Date());
//		role.setRoleName("Test Role-1");
//		try {
//			DBDC dbDc = roleService.createRole(role);
//			System.out.println(dbDc.isSuccess());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	
	@Test
	public void addTemplate(){
		
//		RoleTemplate temp = new RoleTemplate();
//		temp.setRoleTemplateName("Primary Temp");
//		temp.setRolePkey(new BigDecimal(1));
//		temp.setpKey(new BigDecimal(104));
//		temp.setPrimaryRole(true);
//		List<RoleTemplateAppFunMap> roleTemplateAppFunMap =new ArrayList<RoleTemplateAppFunMap>();
//		
//		RoleTemplateAppFunMap r1 = new RoleTemplateAppFunMap();
//		r1.setAppFunctionPkey(new BigDecimal(5));
//		r1.setRoleTemplatePkey(new BigDecimal(104));
//		r1.setpKey(new BigDecimal(102));
//		r1.setRead(1);
//		r1.setExecute(1);
//		roleTemplateAppFunMap.add(r1);
//		templateService.createOrUpdateTemplate(temp, roleTemplateAppFunMap);
		
		
//		RoleTemplate temp1 =templateService.findRoleTemplateById(new BigDecimal(104));
//		System.out.println(temp1.getRoleTemplateName());
//		
//		
//		temp1=templateService.findRoleTemplateByRoleAndName("Primary Temp", new BigDecimal(1));
//		System.out.println(temp1.getRoleTemplateName());
//		
//		List<RoleTemplate> findRoleTemplateByRole = templateService.findRoleTemplateByRole(new BigDecimal(1));
//		System.out.println(findRoleTemplateByRole.get(0));
//		
//		List<RoleTemplateAppFunMap> findRoleTemplateAppFunMapByTemplateId = templateService.findRoleTemplateAppFunMapByTemplateId(new BigDecimal(104));
//		System.out.println(findRoleTemplateAppFunMapByTemplateId.get(0).getAppFunctionPkey());
	}
	
	@Test
	public void addUserRoleMap(){
		
		BigDecimal userPkey = new BigDecimal(1);
		List<AppUserRoleMap> appUserRoleMapList = new ArrayList<AppUserRoleMap>();
		
		AppUserRoleMap aurm1 = new AppUserRoleMap();
		//aurm1.setpKey(new BigDecimal(59));
		aurm1.setRolePkey(new BigDecimal(1));
		aurm1.setRoleTemplatePkey(new BigDecimal(205));
		aurm1.setUserPkey(userPkey);
		
		appUserRoleMapList.add(aurm1);
		
		AppUserRoleMap aurm2 = new AppUserRoleMap();
		//aurm2.setpKey(new BigDecimal(61));
		aurm2.setRolePkey(new BigDecimal(2));
		aurm2.setRoleTemplatePkey(new BigDecimal(222));
		aurm2.setUserPkey(userPkey);
		appUserRoleMapList.add(aurm2);
		
		AppUserRoleMap aurm3 = new AppUserRoleMap();
		//aurm3.setpKey(new BigDecimal(62));
		aurm3.setRolePkey(new BigDecimal(26));
		aurm3.setRoleTemplatePkey(new BigDecimal(203));
		aurm3.setUserPkey(userPkey);
		appUserRoleMapList.add(aurm3);
		
		appUserRoleMapService.createOrUpdateOrDeleteAppUserRoleMap(appUserRoleMapList, userPkey);
	}
}