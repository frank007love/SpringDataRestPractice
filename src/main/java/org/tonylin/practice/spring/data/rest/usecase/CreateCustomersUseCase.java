package org.tonylin.practice.spring.data.rest.usecase;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.tonylin.practice.spring.data.rest.gateway.CustomerRepository;
import org.tonylin.practice.spring.data.rest.repository.Customer;

public class CreateCustomersUseCase {
	private final CustomerRepository customerGateway;
	private final PlatformTransactionManager txManager;
	
	public CreateCustomersUseCase(CustomerRepository customerGateway, PlatformTransactionManager txManager) {
		this.customerGateway = customerGateway;
		this.txManager = txManager;
	}
	
	@Transactional(readOnly=false )
	public Customer createDirectly(Customer customer) throws UseCaseException{
		return customerGateway.save(customer);
	}
	
	
	public void createDirectlyWithTxManager(Customer ... customers) throws UseCaseException{
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setReadOnly(false);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus txStatus = null;
		
		try {
			txStatus = txManager.getTransaction(def);
			for( Customer customer : customers ) {
				customerGateway.save(customer);
			}
			txManager.commit(txStatus);
		} catch (Exception e) {
			if(txStatus != null)
				txManager.rollback(txStatus);
			throw new UseCaseException(e.getMessage());
		}
	}
	
	@Transactional(readOnly=false )
	public void createDirectlyWithTransactionAnnotation(Customer ... customers) throws UseCaseException{
		for( Customer customer : customers ) {
			customerGateway.save(customer);
		}
	}

	@Transactional(readOnly=false )
	public Customer createWithPrecheck(Customer customer) throws UseCaseException{
		if( customer.getId() != null ) {
			throw new UseCaseException("Can't create customer with static id.");
		}
		
		return customerGateway.save(customer);
	}
	
	@Transactional(readOnly=false, rollbackFor=UseCaseException.class)
	public void createWithPrecheckAndThrowCheckedException(Customer... customers) throws UseCaseException{
		for( Customer customer : customers ) {
			if( customer.getId() != null ) {
				throw new UseCaseException("Can't create customer with static id.");
			}
			
			customerGateway.save(customer);
		}
	}
	
	@Transactional(readOnly=false)
	public void createWithPrecheckAndThrowRuntimeException(Customer... customers){
		for( Customer customer : customers ) {
			if( customer.getId() != null ) {
				throw new RuntimeException("Can't create customer with static id.");
			}
			
			customerGateway.save(customer);
		}
	}
}
