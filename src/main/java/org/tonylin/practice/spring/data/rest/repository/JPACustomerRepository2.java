package org.tonylin.practice.spring.data.rest.repository;
///*
// * Copyright 2012 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package org.tonylin.practice.spring.data.rest.repository;
//
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Repository;
//
//@SuppressWarnings("unchecked")
//@Repository
//public interface JPACustomerRepository extends PagingAndSortingRepository<Customer, Long>, 
//														JpaSpecificationExecutor<Customer> {
//	Customer save(Customer entity);
//	
//	void delete(Long id);
//	
//	long count(Specification<Customer> spec);
//	
//	Page<Customer> findAll(Pageable pageable);
//	
//	List<Customer> findAll(Specification<Customer> spec);
//}
//
//
