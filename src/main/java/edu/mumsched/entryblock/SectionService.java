package edu.mumsched.entryblock;

import java.util.List;


public interface SectionService {

	public void save(Section section);

	public Section getSectionBySectionID(Long sectionId);

	public List<Section> getAllSection();

    public void deleteBySectionId(Long SectionId) ;
	
	public void update(Section section);
}
