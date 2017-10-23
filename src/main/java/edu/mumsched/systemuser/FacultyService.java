package edu.mumsched.systemuser;

import java.util.List;



public interface FacultyService {
	public void save(Faculty faculty);
	public void delete(Long id);

	public Faculty getFacultybyFacultyID(int facultyid);

	public Faculty getFacultyByName(String facultyName);

	public List<Faculty> getAllFaculty();
}
