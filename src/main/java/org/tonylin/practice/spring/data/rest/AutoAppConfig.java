package org.tonylin.practice.spring.data.rest;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.tonylin.practice.spring.data.rest.gateway.CustomerRepository;
import org.tonylin.practice.spring.data.rest.gateway.internal.CustomerGatewayImpl;
import org.tonylin.practice.spring.data.rest.usecase.GetCustomersUseCase;
import org.tonylin.practice.spring.data.rest.usecase.CreateCustomersUseCase;

@EnableTransactionManagement
@ComponentScan
@EnableJpaRepositories
@PropertySource("classpath:postgres.properties")
public class AutoAppConfig {

	@Autowired
	Environment env;

	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		ds.setUrl(env.getProperty("jdbc.url"));
		ds.setUsername(env.getProperty("jdbc.username"));
		ds.setPassword(env.getProperty("jdbc.password"));
		return ds;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(getClass().getPackage().getName());
		factory.setDataSource(dataSource());

		return factory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}

//	
//	@Bean
//	public CustomerGateway customerGateway() {
//		return CustomerGateway;
//	}
	
	@Autowired
	private CustomerRepository customerGateway;
	
	@Bean
	public CreateCustomersUseCase updateCustomersUseCase() {
		//return new UpdateCustomersUseCase(CustomerGateway);
		return new CreateCustomersUseCase(customerGateway);
	}
	
	@Bean
	public GetCustomersUseCase getCustomersUseCase() {
		//return new GetCustomersUseCase(CustomerGateway);
		return new GetCustomersUseCase();
	}
}
