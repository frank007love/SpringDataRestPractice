package org.tonylin.practice.spring.data.rest.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.tonylin.practice.spring.data.rest.SharedDBConfig;
import org.tonylin.practice.spring.data.rest.gateway.CustomerRepository;
import org.tonylin.practice.spring.data.rest.repository.Customer;
import org.tonylin.practice.spring.data.rest.repository.JPACustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SharedDBConfig.class, TwoLevelTransactionalTest.AppConfig.class})
public class TwoLevelTransactionalTest {

	@Resource(name="fakeCustomerRepository")
	private CustomerRepository fakeCustomerRepository;
	
	@Resource(name="realCustomerRepository")
	private CustomerRepository realCustomerRepository;
	
	@Autowired
	private CreateCustomersUseCase updateCustomersUseCase;
	
	@Before
	public void setup() {
		fakeCustomerRepository.deleteAll();
	}
	
	@Test
	public void Should_Rollback_When_CreateMultipleCustomerFailed_With_TxManager() throws UseCaseException {
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		
		try {
			updateCustomersUseCase.createDirectlyWithTxManager(customer1, customer2);
			fail("Should be failed.");
		} catch (Exception e) {
			assertEquals(0, realCustomerRepository.findAll().size());
		}
	}
	
	@Test
	public void Should_Rollback_When_CreateMultipleCustomerFailed_With_AnnotationTx() throws UseCaseException {
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		Customer customer3 = new Customer();
		
		try {
			updateCustomersUseCase.createDirectlyWithTransactionAnnotation(customer1, customer2, customer3);
			assertEquals(0, realCustomerRepository.findAll().size());
			//fail("Should be failed.");
		} catch (Exception e) {
			assertEquals(0, realCustomerRepository.findAll().size());
		}
	}
	
	public static class FakeCustomerRepository implements CustomerRepository {
		private CustomerRepository customerRepository;
		
		private int count = 0;
		
		public FakeCustomerRepository(CustomerRepository customerRepository) {
			this.customerRepository = customerRepository;
		}

		@Transactional(readOnly=false)
		@Override
		public Customer save(Customer entity) {
			if( (count++) == 1 )
				throw new RuntimeException();
			
			return customerRepository.save(entity);
		}

		@Override
		public List<Customer> findAll() {
			return customerRepository.findAll();
		}

		@Override
		public void deleteAll() {
			count = 0;
			customerRepository.deleteAll();
		}
		
	}

	public static class AppConfig {
		private CustomerRepository customerRepository;
		
		@Autowired
		private ApplicationContext context;
		
		@Autowired
		private PlatformTransactionManager txManager;
		
		@Bean(name="fakeCustomerRepository")
		public CustomerRepository fakeCustomerRepository() {
			if( customerRepository != null )
				return customerRepository;
			
			return customerRepository = new FakeCustomerRepository(realCustomerRepository());
		}
		
		@Bean(name="realCustomerRepository")
		public CustomerRepository realCustomerRepository() {
			return context.getBean(JPACustomerRepository.class);
		}
		
		@Bean
		public CreateCustomersUseCase createCustomersUseCase() {
			return new CreateCustomersUseCase(fakeCustomerRepository(), txManager);
		}
	}
}
