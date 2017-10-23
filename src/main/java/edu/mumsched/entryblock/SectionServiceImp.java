package edu.mumsched.entryblock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImp implements SectionService{

	@Autowired
	SectionDao SectionDao;
	
	@Override
	public void save(Section section) {
		// TODO Auto-generated method stub
		SectionDao.save(section);
	}

	@Override
	public Section getSectionBySectionID(Long sectionId) {
		// TODO Auto-generated method stub
		return SectionDao.findSectionBySectionID(sectionId);
	}

	@Override
	public List<Section> getAllSection() {
		// TODO Auto-generated method stub
		return SectionDao.getAllSection();
	}

	@Override
	public void deleteBySectionId(Long SectionId) {
		// TODO Auto-generated method stub
		SectionDao.delete(SectionId);
	}

	@Override
	public void update(Section section) {
		// TODO Auto-generated method stub
		SectionDao.save(section);
	}

}
