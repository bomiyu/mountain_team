package org.zerock.service.freply;

import java.util.List;

import org.zerock.domain.freeboard.FCriteria;
import org.zerock.domain.freeboard.FReplyVO;

public interface FReplyService {
	public int register(FReplyVO vo);
	
	public FReplyVO read(Long rno);
	
	public int modify(FReplyVO vo);
	
	public int remove(Long no);
	
	public List<FReplyVO> getList(FCriteria fcri, Long board_no);
}
