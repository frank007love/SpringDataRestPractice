package org.tonylin.practice.spring.data.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.tonylin.practice.spring.data.rest.repository.JPACustomerRepository;
import org.tonylin.practice.spring.data.rest.usecase.CreateCustomersUseCase;

@Import(SharedDBConfig.class)
public class AppConfig {
	
	private static Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	@Bean
	public CreateCustomersUseCase updateCustomersUseCase() {
		JPACustomerRepository customerGateway = context.getBean(JPACustomerRepository.class);
		return new CreateCustomersUseCase(customerGateway, txManager);
	}
}
