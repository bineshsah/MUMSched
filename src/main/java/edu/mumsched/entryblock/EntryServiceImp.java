package edu.mumsched.entryblock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryServiceImp implements EntryService {

	@Autowired
	EntryDao entryDAO;

	public void save(Entry entry) {
		entryDAO.save(entry);
	}

	@Override
	public Entry getEntryByEntryName(String entryname) {
		return entryDAO.findEntryByEntryName(entryname);
	}

	@Override
	public Entry getEntryByEntryID(Long entryID) {
		return entryDAO.findEntryByEntryID(entryID);
	}

	@Override
	public List<Entry> getAllEntry() {
		// TODO Auto-generated method stub
		return entryDAO.getAllEntry();
	}

	@Override
	public void deleteById(Long entryId) {
		// TODO Auto-generated method stub
		
		entryDAO.delete(entryId);
	}

	@Override
	public void update(Entry entry) {
		// TODO Auto-generated method stub
		entryDAO.save(entry);
	}

}
