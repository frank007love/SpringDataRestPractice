/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tonylin.practice.spring.data.rest.repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.util.Assert;

/**
 * A customer.
 * 
 * @author Oliver Gierke
 */
@Entity
//@Table(name="`view_customer`")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="first_name")
	private String firstname;
	
	@Column(name="last_name")
	private String lastname;

	@Column(unique = true)
	private EmailAddress emailAddress;

//	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customer_id")
	private Set<Address> addresses = new HashSet<Address>();
//	
//	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "customer_id")
//	private Address addresses2;

//	/**
//	 * Creates a new {@link Customer} from the given firstname and lastname.
//	 * 
//	 * @param firstname must not be {@literal null} or empty.
//	 * @param lastname must not be {@literal null} or empty.
//	 */
//	public Customer(String firstname, String lastname) {
//
//		Assert.hasText(firstname);
//		Assert.hasText(lastname);
//
//		this.firstname = firstname;
//		this.lastname = lastname;
//	}
//
//	protected Customer() {
//
//	}

//	/**
//	 * Adds the given {@link Address} to the {@link Customer}.
//	 * 
//	 * @param address must not be {@literal null}.
//	 */
//	public void add(Address address) {
//
//		Assert.notNull(address);
//		this.addresses.add(address);
//	}

	/**
	 * Returns the firstname of the {@link Customer}.
	 * 
	 * @return
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Returns the lastname of the {@link Customer}.
	 * 
	 * @return
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Sets the lastname of the {@link Customer}.
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Returns the {@link EmailAddress} of the {@link Customer}.
	 * 
	 * @return
	 */
	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the {@link Customer}'s {@link EmailAddress}.
	 * 
	 * @param emailAddress must not be {@literal null}.
	 */
	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

//	/**
//	 * Return the {@link Customer}'s addresses.
//	 * 
//	 * @return
//	 */
//	public Set<Address> getAddresses() {
//		return Collections.unmodifiableSet(addresses);
//	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
