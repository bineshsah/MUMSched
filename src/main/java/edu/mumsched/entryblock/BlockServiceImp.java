package edu.mumsched.entryblock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlockServiceImp implements BlockService {

	@Autowired
	BlockDao blockDAO;

	public void save(Block block) {
		blockDAO.save(block);
	}

	@Override
	public Block getBlockByBlockName(String blockname) {
		return blockDAO.findBlockByBlockName(blockname);
	}

	@Override
	public Block getBlockByBlockID(Long blockID) {
		return blockDAO.findBlockByBlockID(blockID);
	}

	@Override
	public List<Block> getAllBlock() {
		// TODO Auto-generated method stub
		return blockDAO.getAllBlock();
	}

	@Override
	public void deleteByBlockId(Long blockId) {
		// TODO Auto-generated method stub
		blockDAO.delete(blockId);
	}

	@Override
	public void update(Block block) {
		// TODO Auto-generated method stub
		blockDAO.save(block);
	}

}
