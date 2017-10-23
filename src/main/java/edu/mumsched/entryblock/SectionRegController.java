package edu.mumsched.entryblock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SectionRegController {

	
	private final BlockService blockService;
	
	private final EntryService entryService;
	
	private final SectionService sectionService;
	@ModelAttribute("module")
	public String module() {
		return "section";
	}
	SectionRegController(BlockService blockService,EntryService entryService,SectionService sectionService){
		this.blockService=blockService;
		this.entryService=entryService;
		this.sectionService=sectionService;
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
				entryNameList.add(entry.getEntryName());
			}
			 List<String> blockNameList = new ArrayList<String>();
			    for (Block block : blockService.getAllBlock()) {
					blockNameList.add(block.getBlockName());
				}
			  
			 model.addAttribute("blockNameList", blockNameList);
			model.addAttribute("entryNameList", entryNameList);
    	    return "entryblock/addSection";
    }
	@RequestMapping(value = { "/addnewsection" }, method = RequestMethod.POST)
	public String registerNewBlock(@ModelAttribute("newSection") Section section, Model model ,BindingResult bindingResult) {
//		Entry entry = entryService.getEntryByEntryName(block.getEntryName());
//		entry.addBlock(block);
		sectionService.save(section);
		return "redirect:/section";
	}
}
