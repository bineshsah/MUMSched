package edu.mumsched.entryblock;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
	public String registerNewBlock(@ModelAttribute("newBlock") Block block, Model model) {
		Entry entry = entryService.getEntryByEntryName(block.getEntryName());
		entry.addBlock(block);		
		blockService.save(block);
		
		return "redirect:/block";
	}

	@RequestMapping("/deleteblock")
    public String delete(Long blockID) {
		blockService.deleteByBlockId(blockID);
        return "redirect:/block";
    }
	@RequestMapping("/toEditblock")
    public String toEdit(Model model, Long blockID) {
		Block block = blockService.getBlockByBlockID(blockID);
		model.addAttribute("block", block);
        return "entryblock/UpdateBlock";
    }
	@RequestMapping("/editblock")
    public String editEntry(@ModelAttribute("block") Block block ,BindingResult bindingResult) {
		System.out.println("block================"+block.getEntryName());
		Entry entry = entryService.getEntryByEntryName(block.getEntryName());
		entry.addBlock(block);
        blockService.update(block);
        return "redirect:/block";
    }
}
