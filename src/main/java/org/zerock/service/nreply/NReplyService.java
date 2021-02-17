package org.zerock.service.nreply;

import java.util.List;

import org.zerock.domain.nreply.NReplyVO;

public interface NReplyService {
	
	public int getTotal(Long notice_no);
	
	public int register(NReplyVO reply);
	
	public NReplyVO get(Long no);// 필요?
	
	public boolean modify(NReplyVO reply);
	
	public boolean remove(Long no);
	
	public List<NReplyVO> getList(Long notice_no);
}
