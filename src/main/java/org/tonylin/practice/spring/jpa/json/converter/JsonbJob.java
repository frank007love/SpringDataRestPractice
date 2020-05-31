package org.tonylin.practice.spring.jpa.json.converter;

import java.io.Serializable;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tonylin.practice.spring.jpa.json.JobAttributes;

@Entity
@Table(name="`JsonbJob`")
public class JsonbJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
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
