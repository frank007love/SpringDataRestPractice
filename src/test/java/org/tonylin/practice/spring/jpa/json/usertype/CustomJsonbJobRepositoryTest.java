package org.tonylin.practice.spring.jpa.json.usertype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tonylin.practice.spring.jpa.json.JobAttributes;

import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CustomJsonbJobRepositoryTest {

	@Autowired
	private CustomJsonbJobRepository jobRepository;
	
	@Test
	public void testFind() {
		Iterable<CustomJsonbJob> jobIter =  jobRepository.findAll();
		
		List<CustomJsonbJob> jobs = Lists.newArrayList(jobIter);
		assertEquals(3, jobs.size());
		
		assertEquals(1, jobs.get(0).getId());
		assertEquals("fuck", jobs.get(0).getAttributes().getType());
		assertEquals(2, jobs.get(1).getId());
		assertEquals("fuck2", jobs.get(1).getAttributes().getType());
		assertEquals(3, jobs.get(2).getId());
		assertEquals("fuck3", jobs.get(2).getAttributes().getType());
	}
	
	
	@Test
	public void testSave() {
		JobAttributes attr = new JobAttributes();
		attr.setType("fuck");

		CustomJsonbJob job = new CustomJsonbJob();
		
		try {
			job.setAttributes(attr);
			job = jobRepository.save(job);
			
			job = jobRepository.findOne(job.getId());
			assertNotNull(job);
			assertEquals("fuck", job.getAttributes().getType());
		} finally {
			jobRepository.delete(job);
		}
	}
}