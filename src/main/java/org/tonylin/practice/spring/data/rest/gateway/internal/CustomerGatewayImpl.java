package org.tonylin.practice.spring.data.rest.gateway.internal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.tonylin.practice.spring.data.rest.gateway.CustomerRepository;
import org.tonylin.practice.spring.data.rest.repository.Customer;
import org.tonylin.practice.spring.data.rest.repository.JPACustomerRepository;

public class CustomerGatewayImpl implements CustomerRepository {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public Customer save(Customer entity) {
		return customerRepo.save(entity);
	}

	@Override
	public List<Customer> findAll() {
		return StreamSupport.stream(customerRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
}
