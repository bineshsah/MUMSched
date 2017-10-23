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
	public String registerNewEntry(@ModelAttribute("newEntry") Entry entry, Model model ,BindingResult bindingResult) {         
		entryService.save(entry);
		return "redirect:/entry";
	}
	@RequestMapping("/deleteentry")
    public String delete(Long entryID) {
		entryService.deleteById(entryID);
        return "redirect:/entry";
    }
	@RequestMapping("/toEdit")
    public String toEdit(Model model, Long entryID) {
		Entry entry = entryService.getEntryByEntryID(entryID);
		model.addAttribute("entry", entry);
        return "entryblock/UpdateEntry";
    }
	@RequestMapping("/editentry")
    public String editEntry(@ModelAttribute("entry") Entry entry ,BindingResult bindingResult) {
        entryService.update(entry);
        return "redirect:/entry";
    }
}
