package org.zerock.service.conquest;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.conquest.ConquestVO;
import org.zerock.domain.mountain.ConqStickerVO;
import org.zerock.domain.mountain.MnameVO;
import org.zerock.mapper.ConquestMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//@Component
@Service
@AllArgsConstructor
@Log4j
public class ConquestServiceImpl implements ConquestService {

	private ConquestMapper mapper;

	@Override
	@Transactional
	public int addConquest(ConquestVO cvo) {
		return mapper.addConquest(cvo);
	}
 
	@Override 
	public boolean updateConquest(ConquestVO cvo) { 
		return mapper.updateConquest(cvo) == 1; 
		}

	// for CONQUEST table
	@Override
	public List<MnameVO> getMnameList() {
		return mapper.getMnameList();
	}
	
	@Override
	public List<ConqStickerVO> getConqListbyMem(Long user_no) {
		return mapper.getConqListbyMem(user_no);
	}
	
	
	public int checkCnt(long member_no, long mountain_no) {
		return mapper.checkCnt(member_no, mountain_no);
	}
	/*
	 * @Override public int getConquestCount() { return mapper.getConquestCount(); }
	 */
	/*
	 * @Override public List<ConquestVO> getList() { return mapper.getList(); }
	 * 
	 * @Override public List<ConquestVO> getList(Ccriteria cri) { // pagingì²˜ë¦¬ return
	 * mapper.getListWithPaging(cri); }
	 * 
	 * 
	 * @Override public boolean deleteConquest(Long no) { return mapper.delete(no)
	 * == 1; }
	 * 
	 *
	 * 
	 * @Override public int getTotal(Ccriteria cri) { return
	 * mapper.getConquestCount(cri); }
	 */
}

