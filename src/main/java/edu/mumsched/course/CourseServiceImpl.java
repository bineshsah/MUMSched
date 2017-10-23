package edu.mumsched.course;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private PrerequisiteDao coursePrerequesitDao;

	@Override
	public Course findCourseByCourseCode(String courseCode) {
		return courseDao.findCourseByCourseCode(courseCode);
	}

	@Override
	public Course findCourseByCourseName(String courseName) {
		
		return courseDao.findCourseByCourseName(courseName);
	}

	@Override
	public void save(Course course) {
		courseDao.save(course);

	}

	@Override
	public Course getCourseByCourseId(long id) {
		
		return courseDao.findOne(id);
	}

	@Override
	public List<Course> getAllCourses() {
		
		return (List<Course>) courseDao.findAll();
	}

	@Override
	public void delete(long id) {
		courseDao.delete(id);
		
	}

	@Override
	public void saveFull(Course course) {
		coursePrerequesitDao.save(course.getPrerequisitecourses());
		courseDao.save(course);

	}

	@Override
	public List<Prerequisite> getAllCoursesPrerequsits(String courseName) {
		return (List<Prerequisite>) coursePrerequesitDao.findAllByCourseName(courseName);
	}

}
