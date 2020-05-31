package org.tonylin.practice.spring.jpa.json.usertype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomJsonbJobRepository extends JpaRepository<CustomJsonbJob, Long> {

}
