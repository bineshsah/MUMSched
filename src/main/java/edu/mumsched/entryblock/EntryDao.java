package edu.mumsched.entryblock;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EntryDao extends CrudRepository<Entry, Long> {

	@Query("select s from Entry s where s.entryID= :entryID")
	public Entry findEntryByEntryID(@Param("entryID") Long entryID);

	@Query("select s from Entry s where s.entryName= :entryName")
	public Entry findEntryByEntryName(@Param("entryName") String entryname);

	@Query("select s from Entry s")
	public List<Entry> getAllEntry();
}
