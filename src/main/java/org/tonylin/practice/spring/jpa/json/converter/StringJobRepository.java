package org.tonylin.practice.spring.jpa.json.converter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StringJobRepository extends CrudRepository<StringJob, Long> {

}
