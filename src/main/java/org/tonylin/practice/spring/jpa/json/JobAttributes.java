package org.tonylin.practice.spring.jpa.json;

import java.io.Serializable;

public class JobAttributes implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
