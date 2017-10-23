package edu.mumsched.systemuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StudentServiceImp implements StudentService  {
	
	@Autowired
	StrudentDao studentDAO;
	
	public void save(Student student){
		studentDAO.save(student);
		return ;
	}

	@Override
	public Student getStudentByEmail(String email) {
		return studentDAO.findStudentByEmail(email);
	}

	@Override
	public Student getStudentById(Long studentId) {
		return studentDAO.findStudentById(studentId);
	}
	
	
		   
}
 
