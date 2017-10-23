package edu.mumsched.course;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface PrerequisiteDao extends CrudRepository<Prerequisite, Long>{

	
	@Query("select c from Prerequisite c where c.courseName= :courseName")
	public List<Prerequisite> findAllByCourseName(@Param("courseName") String courseName);

}
