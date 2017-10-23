package edu.mumsched.course;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prerequisite {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    private String courseName;

public Prerequisite() {
	super();
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}

}
