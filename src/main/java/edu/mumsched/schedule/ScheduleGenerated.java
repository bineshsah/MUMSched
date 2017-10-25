package edu.mumsched.schedule;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ScheduleGenerated {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	private String blockName;
	
	private Date fromDate;
	private Date toDate;
	private String mppTrack;
	private String fppTrack;
	private String usTrack;

	@JoinColumn(name="scheduleID",nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Schedule schedule;
	
	
	public ScheduleGenerated() {
		
	}

	
	
	public ScheduleGenerated( String blockName, Date fromDate, Date toDate, String mppTrack, String fppTrack,
			String usTrack) {
		
		this.blockName = blockName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.mppTrack = mppTrack;
		this.fppTrack = fppTrack;
		this.usTrack = usTrack;
	}



	public String getUsTrack() {
		return usTrack;
	}

	public void setUsTrack(String usTrack) {
		this.usTrack = usTrack;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getMppTrack() {
		return mppTrack;
	}
	public void setMppTrack(String mppTrack) {
		this.mppTrack = mppTrack;
	}
	public String getFppTrack() {
		return fppTrack;
	}
	public void setFppTrack(String fppTrack) {
		this.fppTrack = fppTrack;
	}
	@Override
	public String toString() {
		return "GeneratedSchedule [id=" + id + ", blockName=" + blockName + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", mppTrack=" + mppTrack + ", fppTrack=" + fppTrack + "]";
	}
	
	

}
