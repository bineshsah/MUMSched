package edu.mumsched.systemuser;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mumsched.course.Course;
import edu.mumsched.course.CourseService;


@Controller
public class SystemUserController {

	private final SystemUserService systemUserService;
	
	private final FacultyService facultyService;

	private final CourseService courseService;
	@ModelAttribute("module")
	public String module() {
		return "usersystems";
	}
	SystemUserController(SystemUserService systemUserService,FacultyService facultyService,CourseService courseService){
		this.systemUserService=systemUserService;
		this.facultyService=facultyService;
		this.courseService=courseService;
	}

	@RequestMapping(value = "/systemUser/userForm", method = RequestMethod.GET)
	public String SystemUserRegFormList(@Valid @ModelAttribute("newSystemUser") SystemUser systemUser,
			BindingResult bindingResult, RedirectAttributes redirect, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "systemuser/addSystemUser";
		}

		//systemUserService.save(systemUser);

		model.addAttribute("systemuserList", systemUserService.getAllSystemUserName());

		return "systemuser/systemuserList";
	}
	
	@RequestMapping(value = "/systemUser/userAddForm", method = RequestMethod.GET)
	public String SystemUserRegForm(@Valid @ModelAttribute("newSystemUser") SystemUser systemUser, Model model) {
		
		model.addAttribute("newSystemUser",systemUser);
		return "systemuser/addSystemUser";
	}

	@RequestMapping(value = { "/systemUser/add" }, method = RequestMethod.POST)
	public String registerNewSystemUser(@Valid @ModelAttribute("newSystemUser") SystemUser systemUser,
			BindingResult bindingResult, RedirectAttributes redirect, Model model) {
		if (bindingResult.hasErrors()) {
			return "systemuser/addSystemUser";
		}

		systemUserService.save(systemUser);

		model.addAttribute("systemuserList", systemUserService.getAllSystemUserName());

		return "systemuser/systemuserList";
	}

	@RequestMapping(value = "/systemUser/delete", method = RequestMethod.GET)
	public String deletesystemUserForm(@RequestParam("id") long id, Model model) {
		systemUserService.delete(id);
		model.addAttribute("systemuserList", systemUserService.getAllSystemUserName());
		return "systemuser/systemuserList";
	}

	@RequestMapping(value = "/systemUser/edit", method = RequestMethod.GET)
	public String loadSystemUserToUpdate(@RequestParam long id, Model model) {
		SystemUser systemUser = systemUserService.findSystemUserBySystemUserID(id);
		model.addAttribute("newSystemUser", systemUser);
		
		return "systemuser/editSystemUser";
	}
	
	
	@RequestMapping(value = "/systemUser/courseFormList", method = RequestMethod.GET)
	public String courseForm(@RequestParam("id") int id, Model model) {
		List<Course> facultyCourse = facultyService.getFacultybyFacultyID(id).getCourses();
		model.addAttribute("courseList",facultyCourse);
		model.addAttribute("id",id);
		return "systemuser/courseSystemUserList";
	}
	
	
	@RequestMapping(value = "/systemUser/courseForms", method = RequestMethod.GET)
	public String courseFormAdd(@RequestParam("id") int id, Model model) {
		/*List<Course> course=courseService.getAllCourses();*/
			
			List<String>course=new ArrayList<>();
			course.add("mpp");
			course.add("fpp");
			model.addAttribute("courseList",course);
		
		model.addAttribute("id",id);
		return "systemuser/addCourseSystemUser";
		}
	
	
	@RequestMapping(value = "/systemUser/addCourseForm/{id}", method = RequestMethod.POST)
	public String courseRegForm(@ModelAttribute("newCourse") Course course, Model model, @RequestParam("id") long id) {
		Faculty faculty = facultyService.getFacultybyFacultyID((int)id);
		faculty.getCourses().add(course);
		facultyService.save(faculty);
		model.addAttribute("courseList",faculty.getCourses());	
		return "systemuser/courseSystemUserList";
	}

	
	@RequestMapping(value = "/systemUser/specializationForm", method = RequestMethod.GET)
	public String specializationForm(@RequestParam long id, Model model) {
		System.out.println("ID "+id);
		List<String> specialList=new ArrayList<String>();
		List<Faculty> specialization = facultyService.getAllFaculty();
		for(Faculty fac:specialization) {
			specialList.add(fac.getSpecialization());
		}
		model.addAttribute("specialLists", specialList);
		return "systemuser/addSpecialization";
	}
	@RequestMapping(value = "/systemUser/addSpecializationForm", method = RequestMethod.POST)
	public String specializationRegForm(@ModelAttribute("newSpecialization") Faculty faculty, Model model) {
		facultyService.save(faculty);
		List<String> specialList=new ArrayList<String>();
		List<Faculty> specialization = facultyService.getAllFaculty();
		for(Faculty fac:specialization) {
			specialList.add(fac.getSpecialization());
		}
		model.addAttribute("specialLists", specialList);
		return "systemuser/SpecializationSystemUserList";
	}

}
