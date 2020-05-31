package org.tonylin.practice.spring.data.rest.gateway;

import java.util.List;

import org.tonylin.practice.spring.data.rest.repository.Customer;

public interface CustomerRepository {
	Customer save(Customer entity);
	List<Customer> findAll();
}
