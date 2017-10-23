package edu.mumsched.schedule;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @PostConstruct
    private void init() {
        scheduleRepository.save(new Schedule("Oct", "Not automatically generated", "Draft"));
        scheduleRepository.save(new Schedule("Jan", "2017.10.18 15:22:42", "Draft"));
    }
    @Transactional
	public void save(Schedule schedule) {
    	scheduleRepository.save(schedule);		
	}

    Iterable<Schedule> findAll() {
        return scheduleRepository.findAll();
    }
    public Schedule getScheduleByentryName(String entryName) {
    	return scheduleRepository.findScheduleByEntryName(entryName);
    	
    }
    public void deleteSchedule(Long id) {
         scheduleRepository.delete(id);
    }

    public Schedule findOne(Long id) {
        return scheduleRepository.findOne(id);
    }

	
}
