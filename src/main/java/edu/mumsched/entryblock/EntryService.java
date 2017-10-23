package edu.mumsched.entryblock;

import java.util.List;


public interface EntryService {

	public void save(Entry entry);

	public Entry getEntryByEntryID(Long entryid);

	public Entry getEntryByEntryName(String entryName);

	public List<Entry> getAllEntry();
	
	public void deleteById(Long entryId) ;
	
	public void update(Entry entry);
}
