package edu.mumsched.systemuser;


public interface StudentService   {
	
	
	public void save(Student student);
	public Student getStudentById(Long id);
	public Student getStudentByEmail(String email);
	
	
	
		   
}
 
