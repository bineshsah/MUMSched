package edu.mumsched.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mumsched.support.web.MessageHelper;

/**
 * Standard Layout System with Fragment Expressions usage example
 */
@Controller
@Secured("ROLE_USER")
class ScheduleController {
	
	
	private final ScheduleService scheduleService;	
	private final ScheduleGeneratedService scheduleGenService;
	
	Set<String> entryLists;
	

	@Autowired
	public ScheduleController(ScheduleService scheduleService,ScheduleGeneratedService scheduleGenService ) {		
		this.scheduleService = scheduleService;
		this.scheduleGenService=scheduleGenService;
		this.entryLists = new HashSet<String>();
		this.entryLists.add("Jan");
		this.entryLists.add("April");
		this.entryLists.add("Aug");
		this.entryLists.add("Oct");
		this.entryLists.add("Nov");
		this.entryLists.add("Dec");
	}

	@ModelAttribute("module")
	public String module() {
		return "schedules";
	}

	@RequestMapping(value = "createschedule", method = RequestMethod.GET)
	public String scheduleForm(Model model, Schedule schedule) {
		model.addAttribute("entryLists", entryLists);
		model.addAttribute("newSchedule", schedule);
		return "schedule/createschedule";
	}

	@RequestMapping(value = "saveschedule", method = RequestMethod.POST)
	public String createSchedule(@Valid @ModelAttribute("newSchedule") Schedule scheduleForm, Errors errors,
			RedirectAttributes ra, Model model) {
		if (errors.hasErrors()) {
			return "schedule/createschedule";
		}
		if (scheduleService.getScheduleByentryName(scheduleForm.getEntryName()) != null) {
			MessageHelper.addInfoAttribute(ra, "schedule.exist");

		} else {
			scheduleService.save(scheduleForm.createSchedule());
			MessageHelper.addSuccessAttribute(ra, "schedule.created");
		}
		return "redirect:schedule";
	}

	@RequestMapping(value = "schedule", method = RequestMethod.GET)
	public String showSchedules(Model model) {
		model.addAttribute("schedules", scheduleService.findAll());
		return "schedule/schedule-list";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteSchedule(@Valid @PathVariable("id") Long id, Model model, RedirectAttributes ra) {
		try {
			System.out.println("Delete ID goes here "+id);
			scheduleService.deleteSchedule(id);
			MessageHelper.addSuccessAttribute(ra, "schedule.delete");
		} catch (Exception e) {
			MessageHelper.addErrorAttribute(ra, "schedule.delete.fail");
		}

		return "redirect:/schedule";
	}

	@RequestMapping(value = "updateSchedule/{id}", method = RequestMethod.GET)
	public String updateSchedule(@Valid @PathVariable("id") Long id, Model model, RedirectAttributes ra,
			@ModelAttribute("editSchedule") Schedule sched) {
		try {
			Schedule schedule = scheduleService.findOne(id);
			Schedule scheduleForm = new Schedule();
			scheduleForm.setScheduleID(schedule.getScheduleID());
			scheduleForm.setEntryName(schedule.getEntryName());
			scheduleForm.setScheduleGeneratedAt(schedule.getScheduleGeneratedAt());
			scheduleForm.setScheduleStatus(schedule.getScheduleStatus());
			model.addAttribute("entryLists", entryLists);
			List<String> schedStatusList = new ArrayList<String>();
			schedStatusList.add("Draft");
			schedStatusList.add("Ok");
			model.addAttribute("scheduleStatusLists", schedStatusList);
			model.addAttribute("editSchedule", scheduleForm);
			System.out.println("LAst information " + scheduleForm);
		} catch (Exception e) {
			MessageHelper.addWarningAttribute(ra, "schedule.update.idnotfound");
		}

		return "schedule/updateschedule";
	}

	@RequestMapping(value = "saveEditSchedule", method = RequestMethod.POST)
	public String saveEditSchedule(@Valid @ModelAttribute("editSchedule") Schedule scheduleupdateForm, Errors errors,
			RedirectAttributes ra, Model model) {
		System.out.println("Schedule update information " + scheduleupdateForm);
		if (errors.hasErrors()) {
			return "/updateSchedule/id/+" + scheduleupdateForm.getScheduleID();
		}
		Schedule schedule = scheduleService.findOne(scheduleupdateForm.getScheduleID());
		if (schedule == null) {
			MessageHelper.addErrorAttribute(ra, "schedule.update.error");
		} else if ((schedule.getEntryName().equals(scheduleupdateForm.getEntryName())
				&& schedule.getScheduleStatus().equals(scheduleupdateForm.getScheduleStatus()))) {
			MessageHelper.addInfoAttribute(ra, "schedule.update.nochange");
		} else if (scheduleService.getScheduleByentryName(scheduleupdateForm.getEntryName()) != null) {
			MessageHelper.addWarningAttribute(ra, "schedule.exist");
		} else {
			scheduleService.save(scheduleupdateForm);
			MessageHelper.addSuccessAttribute(ra, "schedule.updated");
		}

		return "redirect:/schedule";
	}

	@RequestMapping(value = "schedule/{id}", method = RequestMethod.GET)
	public String findSchedule(@PathVariable("id") Long id, Model model) {
		model.addAttribute("schedule", scheduleService.findOne(id));
		return "schedule/schedule";
	}
	@RequestMapping(value = "generateSchedule/{scheduleID}", method = RequestMethod.GET)
	public String generateSchedule(@Valid @PathVariable("scheduleID") Long scheduleID, Model model, RedirectAttributes ra) {
		try {
			System.out.println("id goes here "+scheduleID);
			Schedule schedule=scheduleService.findOne(scheduleID);
			System.out.println("Schedule goes here "+schedule.toString());
			if(schedule!=null){
				
				scheduleGenService.generateScheduleAndSave(schedule);
				
			
				MessageHelper.addSuccessAttribute(ra, "schedule.gen.message");
			}
			
		} catch (Exception e) {
			MessageHelper.addErrorAttribute(ra, "schedule.gen.error");
			e.getMessage();
			
		}

		return "redirect:/schedule";
	}
	@RequestMapping(value = "viewSchedule/{scheduleID}", method = RequestMethod.GET)
	public String viewSchedules(Model model,@PathVariable("scheduleID") Long scheduleID) {
		Schedule schedule=scheduleService.findOne(scheduleID);
			model.addAttribute("scheduleGenLists",schedule.getGenScheduleList());
		return "schedule/scheduleview";
	}
	
	
}
