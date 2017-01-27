package com.immco.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.immco" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.immco.db.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("hibernate.connection.driver_class"));
		dataSource.setUrl(environment.getRequiredProperty("hibernate.connection.url"));
		dataSource.setUsername(environment.getRequiredProperty("hibernate.connection.username"));
		dataSource.setPassword(environment.getRequiredProperty("hibernate.connection.password"));
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

		properties.put("org.hibernate.c3p0.internal.C3P0ConnectionProvider",
				environment.getRequiredProperty("org.hibernate.c3p0.internal.C3P0ConnectionProvider"));
		properties.put("hibernate.c3p0.min_size", environment.getRequiredProperty("hibernate.c3p0.min_size"));
		properties.put("hibernate.c3p0.max_size", environment.getRequiredProperty("hibernate.c3p0.max_size"));
		properties.put("hibernate.c3p0.timeout", environment.getRequiredProperty("hibernate.c3p0.timeout"));
		properties.put("hibernate.c3p0.max_statements",
				environment.getRequiredProperty("hibernate.c3p0.max_statements"));
		properties.put("hibernate.c3p0.idle_test_period",
				environment.getRequiredProperty("hibernate.c3p0.idle_test_period"));
		properties.put("hibernate.c3p0.acquire_increment",
				environment.getRequiredProperty("hibernate.c3p0.acquire_increment"));
		properties.put("hibernate.connection.driver_class",
				environment.getRequiredProperty("hibernate.connection.driver_class"));
		properties.put("hibernate.connection.url", environment.getRequiredProperty("hibernate.connection.url"));
		properties.put("hibernate.connection.username",
				environment.getRequiredProperty("hibernate.connection.username"));
		properties.put("hibernate.connection.password",
				environment.getRequiredProperty("hibernate.connection.password"));

		properties.put("org.hibernate.context.spi.CurrentSessionContext",
				environment.getRequiredProperty("org.hibernate.context.spi.CurrentSessionContext"));
		properties.put("hibernate.cache.use_second_level_cache",
				environment.getRequiredProperty("hibernate.cache.use_second_level_cache"));
		properties.put("hibernate.connection.password",
				environment.getRequiredProperty("hibernate.connection.password"));

		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}