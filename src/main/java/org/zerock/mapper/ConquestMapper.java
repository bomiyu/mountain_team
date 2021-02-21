package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.conquest.ConquestVO;
import org.zerock.domain.mountain.ConqStickerVO;
import org.zerock.domain.mountain.MnameVO;

public interface ConquestMapper {
//	public int getConquestCount(); // SELECT count(*) FROM Conquest

	public int addConquest(ConquestVO cvo);

	public int updateConquest(ConquestVO cvo);
	// for CONQUEST table
	public List<MnameVO> getMnameList();
	
	public List<ConqStickerVO> getConqListbyMem(Long user_no);// user_no == authUser.no
	
	public int checkCnt();
	
//	public List<ConquestVO> getList();
//	public ConquestVO get(Long no);
//	public int delete(Long no);
}
