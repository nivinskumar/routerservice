package com.immco.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.immco.common.JsonWrapper;
import com.immco.db.remote.ConstructionServiceConfig;
import com.immco.db.remote.RouterServiceConfig;

@Configuration
// @EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.immco")
@PropertySource(value = { "classpath:application.properties", "classpath:microservice.properties" })
public class AppConfig {

	@Autowired
	private Environment env;

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Bean(name = "jsonWrapper")
	public JsonWrapper getJsonWrapper() {
		return new JsonWrapper();
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("hibernate.connection.driver_class"));
		dataSource.setUrl(env.getProperty("hibernate.connection.url"));
		dataSource.setUsername(env.getProperty("hibernate.connection.username"));
		dataSource.setPassword(env.getProperty("hibernate.connection.password"));

		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "constructionServiceConfig")
	public ConstructionServiceConfig getConstructionServiceConfig() {
		int dbServicePort = Integer.parseInt(env.getProperty("db.service.port"));
		String dbServiceIp = env.getProperty("db.service.ip");
		String dbServiceCtx = env.getProperty("db.service.ctx");
		String dbServiceProtocol = env.getProperty("db.service.protocol");
		String securityUserName = env.getProperty("security.user.name");
		String securityPassword = env.getProperty("security.user.password");

		ConstructionServiceConfig dbServiceConfig = new ConstructionServiceConfig();
		dbServiceConfig.setDbServicePort(dbServicePort);
		dbServiceConfig.setDbServiceCtx(dbServiceCtx);
		dbServiceConfig.setDbServiceIp(dbServiceIp);
		dbServiceConfig.setDbServiceProtocol(dbServiceProtocol);
		String baseUrl = dbServiceProtocol + "://" + dbServiceIp + ":" + dbServicePort;
		dbServiceConfig.setBaseUrl(baseUrl);
		dbServiceConfig.setSecurityUserName(securityUserName);
		dbServiceConfig.setSecurityPassword(securityPassword);
		return dbServiceConfig;
	}

	@Bean(name = "routerServiceConfig")
	public RouterServiceConfig getRouterServiceConfig() {
		int routerServicePort = Integer.parseInt(env.getProperty("router.service.port"));
		String routerServiceIp = env.getProperty("router.service.ip");
		String routerServiceCtx = env.getProperty("router.service.ctx");
		String routerServiceProtocol = env.getProperty("router.service.protocol");
		String securityUserName = env.getProperty("security.user.name");
		String securityPassword = env.getProperty("security.user.password");
		String baseUrl = routerServiceProtocol + "://" + routerServiceIp + ":" + routerServicePort;

		RouterServiceConfig r = new RouterServiceConfig();
		r.setBaseUrl(baseUrl);
//		r.setRouterServiceCtx(routerServiceCtx);
		r.setRouterServiceIp(routerServiceIp);
		r.setRouterServicePort(routerServicePort);
		r.setRouterServiceProtocol(routerServiceProtocol);
		r.setSecurityPassword(securityPassword);
		r.setSecurityUserName(securityUserName);

		return r;
	}

}
