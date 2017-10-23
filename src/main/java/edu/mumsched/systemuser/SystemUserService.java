package edu.mumsched.systemuser;




import java.util.List;


public interface SystemUserService {

	public void save(SystemUser systemuser);

	public void delete(long systemuserID);

	public SystemUser findSystemUserBySystemUserID(long systemuserID);

	public SystemUser findSystemUserBySystemUserName(String firstName);

	public List<SystemUser> getAllSystemUserName();
	

}
