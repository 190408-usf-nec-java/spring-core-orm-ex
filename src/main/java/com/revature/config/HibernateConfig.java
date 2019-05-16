package com.revature.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.revature.entities.VideoGame;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	@Value("${JDBC_PASSWORD}")
	String password;
	@Value("${JDBC_URL}")
	String path;
	@Value("${JDBC_LOGIN}")
	String role;

	@Bean
	@Inject
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
		System.out.println("Configuring SessionFactory bean");
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));

		// Set annotated classes
		factoryBean.setAnnotatedClasses(VideoGame.class);

		factoryBean.setDataSource(getDataSource());
		return factoryBean;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		System.out.println("Configuring DataSource bean");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(path);
		dataSource.setUsername(role);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	@Inject
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		System.out.println("Configuring transaction manager");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
}
