package org.tonylin.ssm;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="`ssm_hosts`")
public class Host {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "host_id")
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "host_object_id")
	private HostDetail hostDetail;
	
	public HostDetail getDetail() {
		return hostDetail;
	}
}
