package edu.mumsched.entryblock;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import edu.mumsched.support.web.MessageHelper;


@Controller
public class BlockRegController {

	
	private final BlockService blockService;

	private final EntryService entryService;
	@ModelAttribute("module")
		public String module() {
			return "block";
		}
	BlockRegController(BlockService blockService,EntryService entryService){
		this.blockService=blockService;
		this.entryService=entryService;
	}

	@RequestMapping(value = "/block", method = RequestMethod.GET)
	public String blockRegForm(@ModelAttribute("newBlock") Block block, Model model) {
		List<Block> blockList = new ArrayList<Block>();
		blockList.addAll(blockService.getAllBlock());
		
		model.addAttribute("blockList", blockList);
		model.addAttribute("newBlock", block);
		return "entryblock/BlockForm";
	}
	 @RequestMapping("/addBlock")
	    public String addBlock(@ModelAttribute("newBlock")Block block, Model model){
		 List<String> entryNameList = new ArrayList<String>();
			for (Entry entry : entryService.getAllEntry()) {
				entryNameList.add(entry.getEntryName());
			}
			model.addAttribute("entryNameList", entryNameList);
	    	    return "entryblock/addBlock";
	    }
	@RequestMapping(value = { "/addnewblock" }, method = RequestMethod.POST)
	public String registerNewBlock(@ModelAttribute("newBlock") Block block, Model model ,Errors errors,
			RedirectAttributes ra ,BindingResult bindingResult) {
		System.out.println(bindingResult);
//		if () {
//			
//		}
	    boolean isexist = false;
		for (Block block2: blockService.getAllBlock()) {
			if (block2.getBlockName().equals(block.getBlockName())&&block2.getEntryName().equals(block.getEntryName())) {
				isexist = true;
				break;
			}else {
				isexist = false;
			}
		}
		if (isexist) {
			MessageHelper.addInfoAttribute(ra, "block.exist");

		} else {
			Entry entry = entryService.getEntryByEntryName(block.getEntryName());
			entry.addBlock(block);		
			blockService.save(block);
			MessageHelper.addSuccessAttribute(ra, "block.created");
		}
		return "redirect:/block";
	}

	@RequestMapping("/deleteblock")
    public String delete(Long blockID , RedirectAttributes ra) {
		try {
//			System.out.println("Delete ID goes here "+id);
			blockService.deleteByBlockId(blockID);
			MessageHelper.addSuccessAttribute(ra, "block.delete");
		} catch (Exception e) {
			MessageHelper.addErrorAttribute(ra, "block.delete.fail");
		}
		
        return "redirect:/block";
    }
	@RequestMapping("/toEditblock")
    public String toEdit(Model model, Long blockID, RedirectAttributes ra) {
	try {
		Block block = blockService.getBlockByBlockID(blockID);
		model.addAttribute("block", block);
	} catch (Exception e) {
		MessageHelper.addWarningAttribute(ra, "schedule.update.idnotfound");
	}
        return "entryblock/UpdateBlock";
    }
	@RequestMapping("/editblock")
    public String editEntry(@ModelAttribute("block") Block block ,BindingResult bindingResult,Errors errors,
			RedirectAttributes ra) {
		System.out.println("block================"+block.getEntryName());
		
        boolean isexist = false;
		for (Block block3: blockService.getAllBlock()) {
			if (block3.getBlockName().equals(block.getBlockName())&&block3.getEntryName().equals(block.getEntryName())) {
				isexist = true;
				break;
			}else {
				isexist = false;
			}
		}
        if (errors.hasErrors()) {
			return "/toEditblock/id/+" + block.getBlockID();
		}
		Block block2 = blockService.getBlockByBlockID(block.getBlockID());
		if (block2 == null) {
			MessageHelper.addErrorAttribute(ra, "block.update.error");
		} else if (block2.getEntryName().equals(block.getEntryName())
				&& (block2.getBlockName().equals(block.getBlockName()))
				&& (block2.getFPPNum()==block.getFPPNum())
				&& (block2.getMPPNum()==block.getMPPNum())
				&& (block2.getStartDate().equals(block.getStartDate()))
				&& (block2.getEndDate().equals(block.getEndDate()))) {
			MessageHelper.addInfoAttribute(ra, "block.update.nochange");
		} else if ( (!block2.getBlockName().equals(block.getBlockName()))  && isexist) {
			MessageHelper.addWarningAttribute(ra, "block.exist");
		} else {
			Entry entry = entryService.getEntryByEntryName(block.getEntryName());
			entry.addBlock(block);
	        blockService.update(block);
			MessageHelper.addSuccessAttribute(ra, "block.updated");
		}
        return "redirect:/block";
    }
}
