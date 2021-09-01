package com.umbc.retail.config;

import java.util.HashMap;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.umbc.retail", entityManagerFactoryRef = "EntityManager", transactionManagerRef = "TransactionManager")

public class DatabaseConfig {

	
	@Autowired
	private Environment env;


	@Bean
	public LocalContainerEntityManagerFactoryBean EntityManager()  throws Exception {
		
		try {
			final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
			
			em.setPackagesToScan(new String[] { "com.umbc.retail" });
			
			em.setDataSource(mySqlDataSource());
			
		
			final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
			em.setJpaVendorAdapter(vendorAdapter);
			HashMap<String, Object> properties = new HashMap<>();
			
			
			
			properties.put("hibernate.dialect", env.getProperty("hibernate.con.dialect"));
			properties.put("hibernate.pool_size", env.getProperty("hibernate.con.pool_size"));
			
			
			properties.put("hibernate.show_sql", env.getProperty("hibernate.con.show_sql"));
			properties.put("hibernate.isolation", env.getProperty("hibernate.con.isolation"));
			properties.put("hibernate.isolation", env.getProperty("hibernate.con.isolation"));
			properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.con.hbm2ddl.auto"));
			properties.put("hibernate.format_sql", env.getProperty("hibernate.con.format_sql"));
			properties.put("hibernate.cache.use_second_level_cache",
					env.getProperty("hibernate.con.cache.use_second_level_cache"));
			properties.put("hibernate.cache.use_query_cache", env.getProperty("hibernate.con.cache.use_query_cache"));
			properties.put("hibernate.jdbc.lob.non_contextual_creation",
					env.getProperty("hibernate.con.jdbc.lob.non_contextual_creation"));

			em.setJpaPropertyMap(properties);

			return em;
			}catch(Exception e) {
				
				throw new Exception(e.getMessage());
			}	
				
		}
		

	@Bean
	public DataSource mySqlDataSource() throws Exception {
		try {
			
			HikariDataSource dataSource = new HikariDataSource();
			dataSource.setDriverClassName(env.getProperty("spring.datasource.driver.class"));
			dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
			dataSource.setUsername(env.getProperty("spring.datasource.username"));
			dataSource.setPassword(env.getProperty("spring.datasource.password"));
			dataSource.setMaximumPoolSize(Integer.parseInt(env.getProperty("spring.datasource.max.pool")));
			dataSource.setMinimumIdle(Integer.parseInt(env.getProperty("spring.datasource.min.idle")));

			return dataSource;
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		
		}		
	}
	
		
		@Bean(name = "TransactionManager")
		public PlatformTransactionManager TransactionManager() throws Exception {
			try {
				
				final JpaTransactionManager transactionManager = new JpaTransactionManager();
				transactionManager.setEntityManagerFactory(EntityManager().getObject());
				return transactionManager;
				
			}catch (Exception e) {
				throw new Exception(e.getMessage());
			}		
		}
		
		
		
		
		@Bean
		public MessageSource messageSource() throws Exception {
			try {
				ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
				messageSource.setBasename("messages");
				messageSource.setDefaultEncoding("UTF-8");
				messageSource.setUseCodeAsDefaultMessage(true);
				return messageSource;
			}catch (Exception e) {
				throw new Exception(e.getMessage());
			}		
			
		}

}
