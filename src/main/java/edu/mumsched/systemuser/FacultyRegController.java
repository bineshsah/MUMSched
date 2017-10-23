package edu.mumsched.systemuser;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FacultyRegController {
	
	private final FacultyService facultyService;

	FacultyRegController(FacultyService facultyService){
		this.facultyService=facultyService;
	}
	@RequestMapping(value = "/faculty", method = RequestMethod.GET)
	public String facultyRegForm(@ModelAttribute("newFaculty") Faculty faculty, Model model) {

		List<Faculty> facultyList = new ArrayList<>();
		facultyList.addAll(facultyService.getAllFaculty());
		model.addAttribute("facultyList", facultyList);

		return "addFaculty";
	}

	@RequestMapping(value = { "/addnewfaculty" }, method = RequestMethod.POST)
	public String registerNewFaculty(@Valid @ModelAttribute("newFaculty") Faculty faculty,BindingResult bindingResult,RedirectAttributes redirect, Model model) {
		if(bindingResult.hasErrors()){
			return "addFaculty";
		}
		
		facultyService.save(faculty);
		redirect.addFlashAttribute("faculty",faculty);
		//model.addAttribute("faculty",facultyService.getFacultyByName(faculty.getFacultyName()));
		return "redirect:/welcomefaculty";
	}
	@RequestMapping(value = "/welcomefaculty" , method = RequestMethod.GET)
	public String registerNewFacultywelcome(@ModelAttribute("newFaculty") Faculty faculty, Model model) {

		return "welcomeFaculty";
		
	}
	@RequestMapping(value = "/updateProfile/{facultyid}" , method = RequestMethod.GET)
	public String updateFacultyInfo(@ModelAttribute("newFaculty") Faculty faculty,@PathVariable("facultyid")String facultyid){
		Faculty facultyret=facultyService.getFacultybyFacultyID(Integer.parseInt(facultyid.trim().replace("facultyId=","")));
		System.out.println("Update "+facultyret);
		return "addFaculty";
		
	}

	@RequestMapping(value = "/getFaculties" , method = RequestMethod.GET)
	public String getAllFaculties(@ModelAttribute("newFaculty") Faculty faculty,Model model){
		List<Faculty> facultylist=new ArrayList<>();
		facultylist.addAll(facultyService.getAllFaculty());
		model.addAttribute("facultyList",facultylist);
		return "facultyList";
		
}}
