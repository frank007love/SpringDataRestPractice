package org.tonylin.practice.spring.data.rest.usecase;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tonylin.practice.spring.data.rest.AppConfig;
import org.tonylin.practice.spring.data.rest.gateway.CustomerRepository;
import org.tonylin.practice.spring.data.rest.repository.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CreateCustomersUseCaseTest {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CreateCustomersUseCase updateCustomersUseCase;
	
	@Before
	public void setup() {
		customerRepository.deleteAll();
	}
	
	@Test
	public void Should_ContainOneCustomer_When_CreateNewOne() throws UseCaseException {
		// given
		String expectLastName = "LastName";
		Customer customer = new Customer();
		customer.setLastname(expectLastName);
		
		// when
		updateCustomersUseCase.createDirectly(customer);
		
		// then
		List<Customer> customers = customerRepository.findAll();
		assertEquals(1, customers.size());
		assertEquals(expectLastName, customers.get(0).getLastname());
	}
	
	@Test(expected=UseCaseException.class)
	public void Should_CreateFailed_When_CreateWithStaticId() throws UseCaseException {
		// given
		String expectLastName = "LastName";
		Customer customer = new Customer();
		customer.setLastname(expectLastName);
		customer.setId(123L);
		
		// when
		updateCustomersUseCase.createWithPrecheck(customer);
	}
	
	@Test
	public void Should_CatchCheckedExceptionAndRollback_When_CreateMulipleCustomersWithInvalidCustomer() throws UseCaseException {
		// given
		Customer validCustomer = new Customer();
		validCustomer.setLastname("LastName");
		
		Customer invalidCustomer = new Customer();
		invalidCustomer.setLastname("LastName");
		invalidCustomer.setId(123L);
		
		// when
		try {
			updateCustomersUseCase.createWithPrecheckAndThrowCheckedException(validCustomer, invalidCustomer);
			fail("Should be failed.");
		} catch(UseCaseException e) {
			// then
			assertEquals(0, customerRepository.findAll().size());
		}
	}
	
	@Test
	public void Should_CatchRuntimeExceptionAndRollback_When_CreateMulipleCustomersWithInvalidCustomer() {
		// given
		Customer validCustomer = new Customer();
		validCustomer.setLastname("LastName");
		
		Customer invalidCustomer = new Customer();
		invalidCustomer.setLastname("LastName");
		invalidCustomer.setId(123L);
		
		// when
		try {
			updateCustomersUseCase.createWithPrecheckAndThrowRuntimeException(validCustomer, invalidCustomer);
			fail("Should be failed.");
		} catch(RuntimeException e) {
			// then
			assertEquals(0, customerRepository.findAll().size());
		}
	}
}
