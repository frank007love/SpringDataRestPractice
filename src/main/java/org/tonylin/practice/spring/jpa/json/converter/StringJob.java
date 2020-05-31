package org.tonylin.practice.spring.jpa.json.converter;

import java.io.Serializable;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tonylin.practice.spring.jpa.json.JobAttributes;

@Entity
// https://stackoverflow.com/questions/28571848/spring-boot-jpa-insert-in-table-with-uppercase-name-with-hibernate
@Table(name="`StringJob`")
public class StringJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	// https://github.com/vladmihalcea/hibernate-types
	// https://stackoverflow.com/questions/44308167/how-to-map-mysql-json-column-to-jpa-hibernate-java?rq=1
	@Convert(converter=StringJobAttributesConverter.class)
	private JobAttributes attributes;
	
	public long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public JobAttributes getAttributes() {
		return attributes;
	}


	public void setAttributes(JobAttributes attributes) {
		this.attributes = attributes;
	}
}
