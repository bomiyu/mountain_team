package org.zerock.service.conquest;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.conquest.ConquestVO;
import org.zerock.domain.mountain.ConqStickerVO;
import org.zerock.domain.mountain.MnameVO;

public interface ConquestService {

	public int addConquest(ConquestVO cvo); //ì •ë³µì‚° 
	public boolean updateConquest(ConquestVO cvo);

	// for CONQUEST table
	public List<MnameVO> getMnameList();
	
	public List<ConqStickerVO> getConqListbyMem(Long user_no);

	public int checkCnt(@Param("member_no") long member_no, @Param("mountain_no") long mountain_no);
}

