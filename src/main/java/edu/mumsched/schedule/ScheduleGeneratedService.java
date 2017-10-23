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

   /* @PostConstruct
    private void init() {
    //	scheduleGenRepository.save(new ScheduleGenerated("Jan", new Date(), new Date(),"MPP","FPP"));
    	//scheduleGenRepository.save(new ScheduleGenerated("April", new Date(), new Date(),"MPP","FPP"));
    }*/
    @Transactional
	public void save(ScheduleGenerated scheduleGenerated) {
    	scheduleGenRepository.save(scheduleGenerated);		
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
	ScheduleGenerated scheduleGenerated =new ScheduleGenerated("Jan", new Date(), new Date(),"MPP","FPP");
	System.out.println("generated Schedule goes here "+scheduleGenerated);				
	schedule.addSchedule(scheduleGenerated);
	scheduleGenRepository.save(scheduleGenerated);	

	}
   
}
