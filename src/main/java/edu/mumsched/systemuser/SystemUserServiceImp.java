package edu.mumsched.systemuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class SystemUserServiceImp implements SystemUserService {

	@Autowired
	SystemUserDao systemUserDAO;
	
	@Override
	public void save(SystemUser systemuser) {
		// TODO Auto-generated method stub
		systemUserDAO.save(systemuser);

	}

	@Override
	public void delete(long systemuserID) {
		// TODO Auto-generated method stub
		systemUserDAO.delete(systemuserID);
	}

	@Override
	public SystemUser findSystemUserBySystemUserID(long systemuserID) {
		// TODO Auto-generated method stub
		return systemUserDAO.findSystemUserBySystemUserID(systemuserID);
	}

	@Override
	public SystemUser findSystemUserBySystemUserName(String firstName) {
		// TODO Auto-generated method stub
		return systemUserDAO.findSystemUserBySystemUserName(firstName);
	}

	@Override
	public List<SystemUser> getAllSystemUserName() {
		// TODO Auto-generated method stub
		return systemUserDAO.getAllSystemUserName();
	}

	

	

	

	
}
