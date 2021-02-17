package org.zerock.service.notice;

import java.util.List;

import org.zerock.domain.notice.NCriteria;
import org.zerock.domain.notice.NoticeVO;

public interface NoticeService {

	public int getTotal(NCriteria cri);
	
	public void register(NoticeVO notice);
	
	public NoticeVO get(Long no);
	
	public NoticeVO getWithCnt(Long no);
	
	//public boolean modifyCnt(Long no);
	
	public boolean modify(NoticeVO notice);
	
	public boolean remove(Long no);
	
	public List<NoticeVO> getList();

	public List<NoticeVO> getList(NCriteria cri);
}
