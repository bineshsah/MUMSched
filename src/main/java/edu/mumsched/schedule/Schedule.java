package edu.mumsched.schedule;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Schedule{
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	private String entryName;
	
	private String scheduleGeneratedAt;
	
	private String scheduleStatus;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="schedule")
	private Set<ScheduleGenerated> genScheduleList=new HashSet<ScheduleGenerated>();
	
	public Long getId() {
		return id;
	}
	
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
	
	public Set<ScheduleGenerated> getGenScheduleList() {
		return genScheduleList;
	}

	public void setGenScheduleList(Set<ScheduleGenerated> genScheduleList) {
		this.genScheduleList = genScheduleList;
	}

	public void setId(Long id) {
		this.id = id;
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
        return new Schedule(getEntryName(), "automatically generated", "Draft");
	}
	 

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", entryName=" + entryName + ", scheduleGeneratedAt=" + scheduleGeneratedAt
				+ ", scheduleStatus=" + scheduleStatus + "]";
	}
	

}
