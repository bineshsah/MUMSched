package edu.mumsched.systemuser;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FacultyDao extends CrudRepository<Faculty, Long>{
	@Query("select f from Faculty f where f.facultyId= :facultyId")
	public Faculty findFacultyByID(@Param("facultyId") int facultyId);

	@Query("select f from Faculty f where f.facultyName= :facultyName")
	public Faculty findFacultyByFacultyName(@Param("facultyName") String facultyName);

	@Query("select f from Faculty f")
	public List<Faculty> getAllFaculty();
}
