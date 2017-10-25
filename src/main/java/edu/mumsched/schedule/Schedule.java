package edu.mumsched.schedule;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Schedule{
	@Id
	@GeneratedValue
	private Long scheduleID;
	@NotEmpty
	private String entryName;
	
	private String scheduleGeneratedAt;
	
	private String scheduleStatus;
	
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, mappedBy="schedule")
	private Set<ScheduleGenerated> genScheduleList=new HashSet<ScheduleGenerated>();
	
	
	
	public void addSchedule(ScheduleGenerated scheduleGenerated) {
		genScheduleList.add(scheduleGenerated);
		scheduleGenerated.setSchedule(this);
	}
	
	public Schedule(){
		
	}
	public Schedule(String entryName, String scheduleGeneratedAt, String scheduleStatus) {

		this.entryName = entryName;
		this.scheduleGeneratedAt = scheduleGeneratedAt;
		this.scheduleStatus = scheduleStatus;
	}
	
	public Long getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(Long scheduleID) {
		this.scheduleID = scheduleID;
	}

	public Set<ScheduleGenerated> getGenScheduleList() {
		return genScheduleList;
	}

	public void setGenScheduleList(Set<ScheduleGenerated> genScheduleList) {
		this.genScheduleList = genScheduleList;
	}

	
	public String getEntryName() {
		return entryName;
	}
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	public String getScheduleGeneratedAt() {
		return scheduleGeneratedAt;
	}
	public void setScheduleGeneratedAt(String scheduleGeneratedAt) {
		this.scheduleGeneratedAt = scheduleGeneratedAt;
	}
	public String getScheduleStatus() {
		return scheduleStatus;
	}
	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	public Schedule createSchedule() {
        return new Schedule(getEntryName(), "Generated Automatically", "Draft");
	}

	@Override
	public String toString() {
		return "Schedule [scheduleID=" + scheduleID + ", entryName=" + entryName + ", scheduleGeneratedAt="
				+ scheduleGeneratedAt + ", scheduleStatus=" + scheduleStatus + ", genScheduleList=" + genScheduleList
				+ "]";
	}
	 

}
