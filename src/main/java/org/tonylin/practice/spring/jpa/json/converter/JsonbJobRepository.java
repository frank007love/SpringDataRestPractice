package org.tonylin.practice.spring.jpa.json.converter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonbJobRepository extends JpaRepository<JsonbJob, Long> {

}
