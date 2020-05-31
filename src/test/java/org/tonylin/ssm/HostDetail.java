package org.tonylin.ssm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="`ssm_view_hostdetail`")
public class HostDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "object_id")
	private Integer id;
	
	@Column(name="host_name")
	private String host_name;
	
	
	public String getHostName() {
		return host_name;
	}
}
