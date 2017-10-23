package edu.mumsched.entryblock;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Block {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blockID;

	@NotEmpty
	private String blockName;
	private Date startDate;
	private Date endDate;
	private int FPPNum;
	private int MPPNum;
	private String entryName;
	
	@JoinColumn(name="entryID",nullable=false)
	@ManyToOne(fetch=FetchType.EAGER)
	private Entry entry;
	
	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public Long getBlockID() {
		return blockID;
	}

	public void setBlockID(Long blockID) {
		this.blockID = blockID;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getFPPNum() {
		return FPPNum;
	}

	public void setFPPNum(int fPPNum) {
		FPPNum = fPPNum;
	}

	public int getMPPNum() {
		return MPPNum;
	}

	public void setMPPNum(int mPPNum) {
		MPPNum = mPPNum;
	}

	@Override
	public String toString() {
		return "Block [blockID=" + blockID + ", entryName=" + entryName +", blockName=" + blockName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", FPPNum=" + FPPNum + ", MPPNum=" + MPPNum + "]";
	}

}
