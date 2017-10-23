package edu.mumsched.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class PrerequisteController {
	
	private final CourseService courseService;
	PrerequisteController(CourseService courseService){
		this.courseService=courseService;
	}
	@RequestMapping(value = "/prerequisiteList", method = RequestMethod.GET)
	public String prerequisitecourseList(@RequestParam("courseName") String courseName,Model model) {
		List<Prerequisite> prerequistecourselist = courseService.getAllCoursesPrerequsits(courseName);
		System.out.println(prerequistecourselist);
		model.addAttribute("prerequistecourselist", prerequistecourselist);
		return "course/prerequisite";
}
}