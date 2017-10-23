package edu.mumsched.systemuser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class StudentRegController {
	
	
	private final StudentService studentService;
	
	StudentRegController(StudentService studentService){
		this.studentService=studentService;
	}
	//binesh added /student instead of /
	@RequestMapping(value="/student",method=RequestMethod.GET)
	public String studentRegForm(@ModelAttribute("newStudent") Student student, Model model) {
 
        model.addAttribute("newStudent", student);
          
   	return "systemuser/studentregform";
    }
	
	@RequestMapping(value={"/addnewstudent"}, method = RequestMethod.POST)
    public String registerStudent(@ModelAttribute("newStudent") Student student, Model model) {
		 
	//STUDENT SAVED IN PERSISTENCE
		studentService.save(student);
		
 	//GET STUDENT FROM PERSISTENCE	
 		model.addAttribute(studentService.getStudentByEmail(student.getEmail()));
          
   	return "systemuser/systemuserList";
    }

}
