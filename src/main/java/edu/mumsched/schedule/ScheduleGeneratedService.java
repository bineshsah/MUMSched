package edu.mumsched.schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mumsched.course.CourseService;
import edu.mumsched.entryblock.Block;
import edu.mumsched.entryblock.EntryService;
import edu.mumsched.entryblock.Section;
import edu.mumsched.entryblock.SectionService;

@Service
class ScheduleGeneratedService {

	private final ScheduleGeneratedRepository scheduleGenRepository;
	private final EntryService entryService;
	private final SectionService sectionService;
	private final ScheduleService scheduleService;
	private final CourseService courseService;

	public ScheduleGeneratedService(ScheduleGeneratedRepository scheduleGenRepository, EntryService entryService,
			SectionService sectionService,ScheduleService scheduleService,CourseService courseService) {
		this.scheduleGenRepository = scheduleGenRepository;
		this.entryService = entryService;
		this.sectionService = sectionService;
		this.scheduleService =scheduleService;
		this.courseService= courseService;
	}

	/*
	 * @PostConstruct private void init() { // scheduleGenRepository.save(new
	 * ScheduleGenerated("Jan", new Date(), new Date(),"MPP","FPP"));
	 * //scheduleGenRepository.save(new ScheduleGenerated("April", new Date(), new
	 * Date(),"MPP","FPP")); }
	 */
	@Transactional
	public void save(ScheduleGenerated scheduleGenerated) {
		scheduleGenRepository.save(scheduleGenerated);
	}

	Iterable<ScheduleGenerated> findAll() {
		return scheduleGenRepository.findAll();
	}

	/*
	 * public ScheduleGenerated getScheduleByentryName(String entryName) { return
	 * scheduleGenRepository.findGenScheduleByEntryName(entryName);
	 * 
	 * }
	 */
	public void deleteSchedule(Long id) {
		scheduleGenRepository.delete(id);
	}

	public ScheduleGenerated findOne(Long id) {
		return scheduleGenRepository.findOne(id);
	}

	public void generateScheduleAndSave(Schedule schedule) {

		List<Block> blockLists = new ArrayList<Block>();

		blockLists.addAll(entryService.getEntryByEntryName(schedule.getEntryName()).getBlockList());
		System.out.println(schedule);

		List<Section> sectionList = sectionService.getAllSection();

		for (Block block : blockLists) {
			Set<String> mppCourses = new HashSet<String>();
			Set<String> fppCourses = new HashSet<String>();
			Set<String> usCourses = new HashSet<String>();

			for (Section section : sectionList) {
				if (section.getBlockName().equals(block.getBlockName())) {
					if (section.getTrack().trim().equals("MPP"))
						mppCourses.add(section.getCourseName()+"("+courseService.findCourseByCourseName(section.getCourseName()).getCourseCode()+")");
					if (section.getTrack().trim().equals("FPP"))
						fppCourses.add(section.getCourseName()+"("+courseService.findCourseByCourseName(section.getCourseName()).getCourseCode()+")");
					if (section.getTrack().trim().equals("US"))
						usCourses.add(section.getCourseName()+"("+courseService.findCourseByCourseName(section.getCourseName()).getCourseCode()+")");
				}
			}
			System.out.println("MPP Courses" + mppCourses);
			System.out.println("Fpp Courses " + fppCourses);
			ScheduleGenerated scheduleGenerated = new ScheduleGenerated(block.getBlockName(), block.getStartDate(),
					block.getEndDate(), String.join(",", mppCourses), String.join(",", fppCourses),String.join(",", usCourses));
			schedule.addSchedule(scheduleGenerated);

			System.out.println("generated Schedule goes here " + scheduleGenerated);
			scheduleGenRepository.save(scheduleGenerated);
		}
		schedule.setScheduleStatus("Completed");
		scheduleService.save(schedule);
	}

}
