package edu.mumsched.entryblock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mumsched.course.Course;
import edu.mumsched.course.CourseService;
import edu.mumsched.systemuser.Faculty;
import edu.mumsched.systemuser.FacultyService;



@Controller
public class SectionRegController {

	
	private final BlockService blockService;
	
	private final EntryService entryService;
	
	private final SectionService sectionService;
	
	private final CourseService  courseService;
	
	private final FacultyService facultyService;
	@ModelAttribute("module")
	public String module() {
		return "section";
	}
	SectionRegController(BlockService blockService,EntryService entryService,SectionService sectionService,CourseService courseService ,FacultyService facultyService){
		this.blockService=blockService;
		this.entryService=entryService;
		this.sectionService=sectionService;
		this.courseService = courseService;
		this.facultyService = facultyService;
	}
	
	@RequestMapping(value = "/section", method = RequestMethod.GET)
	public String blockRegForm(@ModelAttribute("newSection") Section section, Model model) {
		List<Section> sectionList = new ArrayList<Section>();
		sectionList.addAll(sectionService.getAllSection());
		model.addAttribute("sectionList", sectionList);
		model.addAttribute("newSection", section);
		return "entryblock/SectionForm";
	}
	@RequestMapping("/addSection")
    public String addSection(@ModelAttribute("newSection") Section section, Model model){
		  List<String> entryNameList = new ArrayList<String>();
			for (Entry entry : entryService.getAllEntry()) {
				for (Block block : entry.getBlockList()) {
					entryNameList.add(entry.getEntryName()+ " " +"-"+ " "+block.getBlockName());
				}
			}
		List<String> courseNameList = new ArrayList<String>();
		for (Course course : courseService.getAllCourses()) {
			courseNameList.add(course.getCourseName());
		}
		List<String> facultyNameList = new ArrayList<String>();
		for (Faculty faculty : facultyService.getAllFaculty()) {
			facultyNameList.add(faculty.getFacultyName());
		}
		model.addAttribute("entryNameList", entryNameList);
		model.addAttribute("facultyNameList", facultyNameList);
		model.addAttribute("courseNameList", courseNameList);
    	    return "entryblock/addSection";
    }
	@RequestMapping(value = { "/addnewsection" }, method = RequestMethod.POST)
	public String registerNewBlock(@ModelAttribute("newSection") Section section, BindingResult bindingResult) {
//		System.out.println("section ============================"+section);
		String [] arr = section.getEntryName().split(" ");
		String entryName = arr[0];
		String blockName = arr[2];
		Block block = blockService.getBlockByBlockName(blockName);
		Entry entry = entryService.getEntryByEntryName(entryName);
		entry.addBlock(block);
		section.setBlockName(blockName);
		section.setEntryName(entryName);
		sectionService.save(section);
		return "redirect:/section";
	}
	@RequestMapping("/deletesection")
    public String delete(Long sectionID) {
		sectionService.deleteBySectionId(sectionID);
        return "redirect:/section";
    }
	@RequestMapping("/toEditsection")
    public String toEdit(Model model, Long sectionID) {
		 List<String> entryNameList = new ArrayList<String>();
			for (Entry entry : entryService.getAllEntry()) {
				for (Block block : entry.getBlockList()) {
					entryNameList.add(entry.getEntryName()+ " " +"-"+ " "+block.getBlockName());
				}
			}
			List<String> courseNameList = new ArrayList<String>();
			for (Course course : courseService.getAllCourses()) {
				courseNameList.add(course.getCourseName());
			}
			List<String> facultyNameList = new ArrayList<String>();
			for (Faculty faculty : facultyService.getAllFaculty()) {
				facultyNameList.add(faculty.getFacultyName());
			}
			model.addAttribute("facultyNameList", facultyNameList);
			model.addAttribute("courseNameList", courseNameList);
		model.addAttribute("entryNameList", entryNameList);
		Section section = sectionService.getSectionBySectionID(sectionID); 
		section.setEntryName(section.getEntryName()+" " +"-"+ " "+section.getBlockName());
	    model.addAttribute("section",section);
        return "entryblock/UpdateSection";
    }
	@RequestMapping("/editsection")
    public String editEntry(@ModelAttribute("section") Section section,BindingResult bindingResult) {
		System.out.println("section=================="+section);
		String [] arr = section.getEntryName().split(" ");
		String entryName = arr[0];
		String blockName = arr[2];
		Block block = blockService.getBlockByBlockName(blockName);
		Entry entry = entryService.getEntryByEntryName(entryName);
		entry.addBlock(block);
		section.setBlockName(blockName);
		section.setEntryName(entryName);
        sectionService.update(section);
        return "redirect:/section";
    }
}
