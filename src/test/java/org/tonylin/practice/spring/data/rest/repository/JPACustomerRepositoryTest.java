package org.tonylin.practice.spring.data.rest.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tonylin.practice.spring.data.rest.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class JPACustomerRepositoryTest {

	@Autowired
	private JPACustomerRepository repository;
	
	@Test
	public void test() {
		repository.findAll();
	}

}
//15:33:37 DEBUG[main] (AppConfig.java:dataSource:45) - db url: jdbc:postgresql://localhost:5432/test
//15:33:38 ERROR[main] (SchemaUpdate.java:execute:261) - HHH000388: Unsuccessful: alter table "ssm_hosts" add constraint FK_88imu9n9v4sgyyufwoxfpqhbj foreign key (host_object_id) references "ssm_view_hostdetail"
//15:33:38 ERROR[main] (SchemaUpdate.java:execute:262) - ERROR: referenced relation "ssm_view_hostdetail" is not a table
//15:33:38 DEBUG[main] (AppConfig.java:dataSource:45) - db url: jdbc:postgresql://localhost:5432/test
//15:33:38 DEBUG[main] (AppConfig.java:dataSource:45) - db url: jdbc:postgresql://localhost:5432/test
//org.springframework.data.jpa.repository.support.SimpleJpaRepository@6f3f0fae
//15:33:38 DEBUG[main] (SqlStatementLogger.java:logStatement:109) - select host0_.host_id as host_id1_0_, host0_.host_object_id as host_obj2_0_ from "ssm_hosts" host0_