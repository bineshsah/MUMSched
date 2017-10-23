package edu.mumsched.schedule;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class ScheduleGeneratedService {

    private final ScheduleGeneratedRepository scheduleGenRepository;

    public ScheduleGeneratedService(ScheduleGeneratedRepository scheduleGenRepository) {
        this.scheduleGenRepository = scheduleGenRepository;
    }

    @PostConstruct
    private void init() {
    	
    }
    @Transactional
	public void save(ScheduleGenerated schedule) {
    	scheduleGenRepository.save(schedule);		
	}

    Iterable<ScheduleGenerated> findAll() {
        return scheduleGenRepository.findAll();
    }
   /* public ScheduleGenerated getScheduleByentryName(String entryName) {
    	return scheduleGenRepository.findGenScheduleByEntryName(entryName);
    	
    }*/
    public void deleteSchedule(Long id) {
         scheduleGenRepository.delete(id);
    }

    public ScheduleGenerated findOne(Long id) {
        return scheduleGenRepository.findOne(id);
    }

	public void generateScheduleAndSave(Schedule schedule) {		;
		schedule.addSchedule(scheduleGenRepository.save(new ScheduleGenerated("Jan", new Date(), new Date(),"MPP","FPP")));
		
	}
   
}
