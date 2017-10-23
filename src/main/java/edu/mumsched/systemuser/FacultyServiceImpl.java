package edu.mumsched.systemuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FacultyServiceImpl implements FacultyService {
	@Autowired
	FacultyDao facultyDao;

	@Override
	public void save(Faculty faculty) {
		facultyDao.save(faculty);

	}

	@Override
	public Faculty getFacultybyFacultyID(int facultyID) {
		// TODO Auto-generated method stub
		return facultyDao.findFacultyByID(facultyID);
	}

	@Override
	public Faculty getFacultyByName(String facultyName) {
		// TODO Auto-generated method stub
		return facultyDao.findFacultyByFacultyName(facultyName);
	}

	@Override
	public List<Faculty> getAllFaculty() {
		// TODO Auto-generated method stub
		return facultyDao.getAllFaculty();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		facultyDao.delete(id);
	}

}
