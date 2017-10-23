package edu.mumsched.course;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseController {
	
	private final CourseService courseService;
	@ModelAttribute("module")
	public String module() {
		return "course";
	}
	CourseController(CourseService courseService){
		this.courseService=courseService;
	}
	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String courseList(Model model) {
		List<Course> courselist = courseService.getAllCourses();
		model.addAttribute("courselist", courselist);
		return "course/courseList";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCourseForm(@ModelAttribute("newCourse") Course course, Model model) {
		model.addAttribute("newCourse", new Course());
		return "course/addCourse1";
	}

	@RequestMapping(value = { "/addnewcourse" }, method = RequestMethod.POST)
	public String addNewCourse(@Valid @ModelAttribute("newCourse") Course course, BindingResult result,
			RedirectAttributes redirect, Model model) {
		if (result.hasErrors()) {
			return "course/addCourse1";
		}
		//courseService.save(course);
		courseService.saveFull(course);
		redirect.addFlashAttribute("course", course);
		return "redirect:/course";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteCourseForm(@RequestParam("id") long id, Model model) {
		courseService.delete(id);
		return "redirect:/course";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editCourseForm(@RequestParam("id") long id, Model model) {
		Course course=courseService.getCourseByCourseId(id);
		model.addAttribute("newCourse",course);
		return "course/editCourse";
	
}
	@RequestMapping(value = "/addprerequisite", method = RequestMethod.GET)
	public String addprerequisite(@RequestParam("id") long id, Model model) {
		Course course=courseService.getCourseByCourseId(id);
		model.addAttribute("newCourse",course);
		return "course/prerequisite";
	
}
}