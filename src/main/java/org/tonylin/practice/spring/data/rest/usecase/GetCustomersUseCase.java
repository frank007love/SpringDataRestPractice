package org.tonylin.practice.spring.data.rest.usecase;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.tonylin.practice.spring.data.rest.gateway.CustomerRepository;
import org.tonylin.practice.spring.data.rest.repository.Customer;

public class GetCustomersUseCase {
	@Autowired
	private CustomerRepository customerRespository;
	
//	public GetCustomersUseCase( CustomerRepository customerGateway) {
//		this.customerRespository = customerGateway;
//	}
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	public List<Customer> execute(){
		
		return customerRespository.findAll();
	}
	
	@javax.transaction.Transactional
	public List<Customer> executeWithJpaTransaction(){
		return customerRespository.findAll();
	}
	
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	public List<Customer> executeWithSpringTransactionReadonly(){
		return customerRespository.findAll();
	}
	
	@org.springframework.transaction.annotation.Transactional
	public List<Customer> executeWithSpringTransaction(){
		return customerRespository.findAll();
	}
}


