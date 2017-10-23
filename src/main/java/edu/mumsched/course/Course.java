package edu.mumsched.course;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


@Entity
public class Course {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private long id;
@NotEmpty
private String courseName;
//@CourseCode
private String courseCode;
private String courseArea;


@OneToMany
@JoinColumn(name="prerequisitecourses_iddddd")
private List<Prerequisite> prerequisitecourses;
//@ManyToMany
//private List<Faculty> faculties;
//@OneToMany
//private List<Section> sections;


public Course() {
	
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getCourseCode() {
	return courseCode;
}
public void setCourseCode(String courseCode) {
	this.courseCode = courseCode;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getCourseArea() {
	return courseArea;
}
public void setCourseArea(String courseArea) {
	this.courseArea = courseArea;
}

	public List<Prerequisite> getPrerequisitecourses() {
		return prerequisitecourses;
	}

public void setPrerequisitecourses(List<Prerequisite> prerequisitecourses) {
	this.prerequisitecourses = prerequisitecourses;
}
//public List<Faculty> getFaculties() {
//	return faculties;
//}
//public void setFaculties(List<Faculty> faculties) {
//	this.faculties = faculties;
//}
//public List<Section> getSections() {
//	return sections;
//}
//public void setSections(List<Section> sections) {
//	this.sections = sections;
//}


}