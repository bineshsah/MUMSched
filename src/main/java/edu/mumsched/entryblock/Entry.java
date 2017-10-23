package edu.mumsched.entryblock;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Entry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long entryID;
	@NotEmpty
	private String entryName;
	private int FPPNum;
	private int MPPNum;
	private Date startDate;
	private Date endDate;

	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.MERGE, mappedBy = "entry")
	private Set<Block> blockList = new HashSet<Block>();

	public void addBlock(Block block) {
	blockList.add(block);
	block.setEntry(this); }
	 
	public Set<Block> getBlockList() {
		return blockList;
	}

	public void setBlockList(Set<Block> blockList) {
		this.blockList = blockList;
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

	/*
	 * public void addBlock(Block block) { blockList.add(block);
	 * block.setEntitys((Entity) this); }
	 * 
	 * public void removeBlock(Block block) { block.setEntitys(null);
	 * this.blockList.remove(block); }
	 */
	public Long getEntryID() {
		return entryID;
	}


	public void setEntryID(Long entryID) {
		this.entryID = entryID;
	}
     
	public String getEntryName() {
		return entryName;
	}

	


	public void setEntryName(String entryName) {
		this.entryName = entryName;
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

//	@Override
//	public String toString() {
//		return "Entry [entryID=" + entryID + ", entryName=" + entryName + ", FPPNum=" + FPPNum + ", MPPNum=" + MPPNum
//				+ ", startDate=" + startDate + ", endDate=" + endDate + ", blockList=" + blockList + "]";
//	}

}
