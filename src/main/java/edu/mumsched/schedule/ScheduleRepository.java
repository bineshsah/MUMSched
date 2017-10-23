package edu.mumsched.schedule;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {

	Schedule findScheduleByEntryName(String entryName);

}
