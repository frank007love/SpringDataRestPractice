package org.tonylin.practice.spring.data.rest.usecase;

import org.springframework.transaction.annotation.Transactional;
import org.tonylin.practice.spring.data.rest.gateway.CustomerRepository;
import org.tonylin.practice.spring.data.rest.repository.Customer;

public class CreateCustomersUseCase {
	private CustomerRepository customerGateway;
	
	public CreateCustomersUseCase(CustomerRepository customerGateway) {
		this.customerGateway = customerGateway;
	}
	
	@Transactional(readOnly=false )
	public Customer createDirectly(Customer customer) throws UseCaseException{
		return customerGateway.save(customer);
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
