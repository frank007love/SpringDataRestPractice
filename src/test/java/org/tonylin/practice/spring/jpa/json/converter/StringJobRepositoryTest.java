package org.tonylin.practice.spring.jpa.json.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tonylin.practice.spring.jpa.json.JobAttributes;
import org.tonylin.practice.spring.jpa.json.converter.StringJob;
import org.tonylin.practice.spring.jpa.json.converter.StringJobRepository;

import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseAppConfig.class)
public class StringJobRepositoryTest {

	@Autowired
	private StringJobRepository jobRepository;
	
	//https://blog.csdn.net/wiselyman/article/details/84902654
	//https://wiselyman.iteye.com/blog/2381631
		//https://stackoverflow.com/questions/43900457/how-do-i-use-spring-data-jpa-to-query-jsonb-column
	// https://stackoverflow.com/questions/10117026/convert-iterator-to-arraylist
	@Test
	public void testFind() {
		Iterable<StringJob> jobIter =  jobRepository.findAll();
		
		List<StringJob> jobs = Lists.newArrayList(jobIter);
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

		StringJob job = new StringJob();
		
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
