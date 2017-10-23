package edu.mumsched.course;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CourseDao extends CrudRepository<Course, Long> {
	@Query("select c from Course c where c.courseCode= :courseCode")
	public Course findCourseByCourseCode(@Param("courseCode") String courseCode);

	@Query("select c from Course c where c.courseName= :courseName")
	public Course findCourseByCourseName(@Param("courseName") String courseName);

}
