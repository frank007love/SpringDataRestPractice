package org.tonylin.practice.spring.jpa.json.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.tonylin.practice.spring.jpa.json.JobAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;


@Converter(autoApply = true)
public class StringJobAttributesConverter implements AttributeConverter<JobAttributes, String> {

	@Override
	public String convertToDatabaseColumn(JobAttributes attribute) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(attribute);
		} catch( Exception e ) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public JobAttributes convertToEntityAttribute(String dbData) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(dbData, JobAttributes.class);
		} catch( Exception e ) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
