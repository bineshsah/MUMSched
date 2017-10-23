package edu.mumsched.course;



import java.util.List;

public interface CourseService {
	public Course findCourseByCourseCode(String courseCode);
	public Course findCourseByCourseName( String courseName);
	public void save (Course course);
	public Course getCourseByCourseId(long id);
	public List<Course> getAllCourses();
	public void delete(long id );
	public void saveFull(Course course);
	
	public List<Prerequisite> getAllCoursesPrerequsits(String courseName);

}
