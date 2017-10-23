package edu.mumsched.systemuser;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class SystemUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long systemuserID;

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String type;
	private String studentTrack;
	private String studentEntry;

	
	public long getSystemuserID() {
		return systemuserID;
	}

	public void setSystemuserID(long systemuserID) {
		this.systemuserID = systemuserID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStudentTrack() {
		return studentTrack;
	}

	public void setStudentTrack(String studentTrack) {
		this.studentTrack = studentTrack;
	}

	public String getStudentEntry() {
		return studentEntry;
	}

	public void setStudentEntry(String studentEntry) {
		this.studentEntry = studentEntry;
	}

	@Override
	public String toString() {
		return "SystemUser [systemuserID=" + systemuserID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + ", type=" + type + ", studentTrack="
				+ studentTrack + ", studentEntry=" + studentEntry + "]";
	}

}
