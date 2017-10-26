package edu.mumsched.entryblock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import edu.mumsched.support.web.MessageHelper;

@Controller
public class EntryRegController {

	@Resource
	EntryService entryService;
	 SimpleDateFormat  SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
	 
	 @ModelAttribute("module")
		public String module() {
			return "entry";
		}

	 
	@RequestMapping(value = "/entry", method = RequestMethod.GET)
	public String entryRegForm(@ModelAttribute("newEntry") Entry entry, Model model) {
		List<Entry> entryList = new ArrayList<Entry>();
		entryList.addAll(entryService.getAllEntry());
		model.addAttribute("entryList", entryList);
		model.addAttribute("newEntry", entry);
		return "entryblock/EntryForm";
	}
    @RequestMapping("/addEntry")
    public String addEntry(){
    	    return "entryblock/addEntry";
    }
	@RequestMapping(value = { "/addnewentry" }, method = RequestMethod.POST)
	public String registerNewEntry(@ModelAttribute("newEntry") Entry entry, Model model ,BindingResult bindingResult, RedirectAttributes ra) {         
		if (entryService.getEntryByEntryName(entry.getEntryName())!=null) {
			MessageHelper.addInfoAttribute(ra, "schedule.exist");

		} else {
			entryService.save(entry);
			MessageHelper.addSuccessAttribute(ra, "schedule.created");
		}
		
		return "redirect:/entry";
	}
	@RequestMapping("/deleteentry")
    public String delete(Long entryID, RedirectAttributes ra) {
		try {
//			System.out.println("Delete ID goes here "+id);
			entryService.deleteById(entryID);
			MessageHelper.addSuccessAttribute(ra, "entry.delete");
		} catch (Exception e) {
			MessageHelper.addErrorAttribute(ra, "entry.delete.fail");
		}
		
        return "redirect:/entry";
    }
	@RequestMapping("/toEdit")
    public String toEdit(Model model, Long entryID, RedirectAttributes ra) {
	try {
		Entry entry = entryService.getEntryByEntryID(entryID);
		model.addAttribute("entry", entry);
	} catch (Exception e) {
		MessageHelper.addWarningAttribute(ra, "schedule.update.idnotfound");
	}
        return "entryblock/UpdateEntry";
    }
	@RequestMapping("/editentry")
    public String editEntry(@ModelAttribute("entry") Entry entry ,BindingResult bindingResult, RedirectAttributes ra) {
		Entry entry2 = entryService.getEntryByEntryID(entry.getEntryID());
		if (entry2 == null) {
			MessageHelper.addErrorAttribute(ra, "entry.update.error");
		} else if (entry2.getEntryName().equals(entry.getEntryName())
				 && (entry2.getFPPNum()==entry.getFPPNum())
				 && (entry2.getMPPNum()==entry.getMPPNum())
				 && (entry2.getStartDate().equals(entry.getStartDate()))
				 && (entry2.getEndDate().equals(entry.getEndDate()))) {
			MessageHelper.addInfoAttribute(ra, "entry.update.nochange");
		} else if (entryService.getEntryByEntryName(entry.getEntryName())!=null) {
			MessageHelper.addWarningAttribute(ra, "schedule.exist");
		} else {
			 entryService.update(entry);
			MessageHelper.addSuccessAttribute(ra, "schedule.updated");
		} 
        return "redirect:/entry";
    }
}
