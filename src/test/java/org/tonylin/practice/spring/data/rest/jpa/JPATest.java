package org.tonylin.practice.spring.data.rest.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Random;
import java.util.function.Supplier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tonylin.practice.spring.data.rest.AppConfig;
import org.tonylin.practice.spring.data.rest.gateway.CustomerRepository;
import org.tonylin.practice.spring.data.rest.repository.Customer;
import org.tonylin.practice.spring.data.rest.repository.JPACustomerRepository;
import org.tonylin.practice.spring.data.rest.usecase.GetCustomersUseCase;
import org.tonylin.practice.spring.data.rest.usecase.CreateCustomersUseCase;
import org.tonylin.practice.spring.data.rest.usecase.UseCaseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class JPATest {

	@Autowired
	private CreateCustomersUseCase updateCustomersUseCase;
	
	@Autowired
	private GetCustomersUseCase getCustomerUseCase;
	
	@Autowired
	private JPACustomerRepository repository;
	
//	@Autowired
//	CustomerRepository repository;
	
//	@Test(expected=UseCaseException.class)
//	public void testTransaction() throws Exception {
//		Random random = new Random();
//		String randomVal1 = String.valueOf(random.nextInt());
//		String randomVal2 = String.valueOf(random.nextInt());
//		
//		
//		try {
//			//repository.findAll().stream().forEach(System.out::println);
//			
//			Customer customer1 = repository.findOne(100L);
//			Customer customer2 = repository.findOne(101L);
////			System.out.println(customer1);
////			System.out.println(customer2);
//			customer1.setLastname(randomVal1);
//			customer2.setLastname(randomVal2);
//			
//			updateCustomersUseCase.execute(customer1, customer2);
//		} finally {
////			assertNotEquals(randomVal1, repository.findOne(100L).getLastname());
////			assertNotEquals(randomVal2, repository.findOne(101L).getLastname());
//		}
//	}
	
	@Test
	public void testGetAll() {
		//getCustomerUseCase.executeWithSpringTransaction();
		int round = 1000;
		double temp = getAverage(round, ()->getCustomerUseCase.execute().size());
		
		double av1 = getAverage(round, ()->getCustomerUseCase.execute().size());
		
		double av2 = getAverage(round, ()->getCustomerUseCase.executeWithJpaTransaction().size());
		
		double av3 = getAverage(round, ()->getCustomerUseCase.executeWithSpringTransaction().size());
		
		double av4 = getAverage(round, ()->getCustomerUseCase.executeWithSpringTransactionReadonly().size());
		
		System.out.println("Average with no transaction(buff): " + temp);
		System.out.println("Average with no transaction: " + av1);
		System.out.println("Average with jpa transaction: " + av2);
		System.out.println("Average with spring transaction: " + av3);
		System.out.println("Average with spring readonly transaction: " + av4);
	}
	
	public double getAverage(int round, Supplier<Integer> countCustomer) {
		double sum = 0;
		for( int i = 0 ; i < round ; i++ ) {
			long before = System.currentTimeMillis();
			assertEquals(3, countCustomer.get().intValue());
			sum += (System.currentTimeMillis()-before);
		}
		return sum / round;
	}
	
}
