package edu.mumsched.systemuser;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;





@Repository
public interface SystemUserDao extends PagingAndSortingRepository<SystemUser, Long> {

	@Query("select s from SystemUser s where s.systemuserID= :systemuserID")
	public SystemUser findSystemUserBySystemUserID(@Param("systemuserID") long systemuserID);

	@Query("select s from SystemUser s where s.firstName= :firstName")
	public SystemUser findSystemUserBySystemUserName(@Param("firstName") String firstName);

	@Query("select s from SystemUser s")
	public List<SystemUser> getAllSystemUserName();

}
