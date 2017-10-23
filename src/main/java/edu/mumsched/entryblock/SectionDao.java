package edu.mumsched.entryblock;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionDao extends CrudRepository<Section, Long>{

	@Query("select s from Section s where s.sectionID= :sctionID")
	public Section findSectionBySectionID(@Param("sctionID") Long sctionID);

//	@Query("select s from Block s where s.blockName= :blockName")
//	public Block findBlockByBlockName(@Param("blockName") String blockname);

	@Query("select s from Section s")
	public List<Section> getAllSection();
}
