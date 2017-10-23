package edu.mumsched.entryblock;

import java.util.List;

public interface BlockService {

	public void save(Block block);

	public Block getBlockByBlockID(Long blockid);

	public Block getBlockByBlockName(String blockName);

	public List<Block> getAllBlock();

    public void deleteByBlockId(Long blockId) ;
	
	public void update(Block block);
}
