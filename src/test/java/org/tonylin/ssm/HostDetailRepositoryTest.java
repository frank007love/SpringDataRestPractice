package org.tonylin.ssm;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class HostDetailRepositoryTest {

	@Autowired
	private HostDetailRepository repository;
	
	@Test
	public void testGet() {
		System.out.println(repository);
		Iterable<Host> hostIter =  repository.findAll();
		
		List<Host> hosts = Lists.newArrayList(hostIter);
		
		System.out.println(hosts.size());
		for( Host host : hosts ) {
			System.out.println(host.getDetail().getHostName());
			break;
		}
	}
}
