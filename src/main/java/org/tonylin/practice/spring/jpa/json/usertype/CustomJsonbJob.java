package org.tonylin.practice.spring.jpa.json.usertype;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.tonylin.practice.spring.jpa.json.JobAttributes;


@Entity
@Table(name="`JsonbJob`")
@TypeDef(name = "jsonb", typeClass = JSONBUserType.class, parameters = {
	    @Parameter(name = JSONBUserType.CLASS, value = "org.tonylin.practice.spring.jpa.json.JobAttributes")})
public class CustomJsonbJob {

	@Id
	@GeneratedValue
	private long id;
	
	@Type(type = "jsonb")
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
