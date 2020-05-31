package org.tonylin.practice.spring.data.rest.usecase;

import javax.annotation.Resource;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Isolation;
import org.tonylin.practice.spring.data.rest.gateway.CustomerRepository;
import org.tonylin.practice.spring.data.rest.repository.Customer;

public class UpdateCustomersUseCase {
	@Resource
	private CustomerRepository customerGateway;
	
//	public UpdateCustomersUseCase(CustomerGateway customerGateway) {
//		this.customerGateway = customerGateway;
//	}
	
	//@Transactional(rollbackOn=UseCaseException.class)
	@org.springframework.transaction.annotation.Transactional(readOnly=false, isolation=Isolation.SERIALIZABLE )
	public void execute(Customer ... customers) throws UseCaseException{
		System.out.println(customerGateway);

		int count = 0;
		for( Customer customer : customers ) {
			if( count != 0 )
				throw new UseCaseException("shit");

			customerGateway.save(customer);
			try {
				Thread.sleep(20*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
	}

}
