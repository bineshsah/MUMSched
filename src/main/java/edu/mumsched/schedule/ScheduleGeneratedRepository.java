package edu.mumsched.schedule;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ScheduleGeneratedRepository extends PagingAndSortingRepository<ScheduleGenerated, Long> {

	/*ScheduleGenerated findGenScheduleByEntryName(String entryName);*/

}
