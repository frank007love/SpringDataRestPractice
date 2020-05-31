package org.tonylin.practice.spring.data.rest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.tonylin.practice.spring.jpa.json.JobAttributes;
import org.tonylin.practice.spring.jpa.json.converter.StringJob;
import org.tonylin.practice.spring.jpa.json.converter.StringJobRepository;

public class Tester {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.start();
		StringJobRepository jobRepository =  context.getBean(StringJobRepository.class);
		
		System.out.println(jobRepository);
		
		JobAttributes attr = new JobAttributes();
		attr.setType("fuck");

		StringJob job = new StringJob();
		
		job.setAttributes(attr);
		jobRepository.save(job);
		
		context.close();
	}

}
