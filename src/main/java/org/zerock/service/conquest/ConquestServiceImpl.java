package org.zerock.service.conquest;

import org.springframework.stereotype.Service;
import org.zerock.domain.conquest.ConquestVO;
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
	public void addConquest(ConquestVO cvo) {
		mapper.addConquest(cvo);
	}
	/*
	 * @Override public List<ConquestVO> getList() { return mapper.getList(); }
	 * 
	 * @Override public List<ConquestVO> getList(Ccriteria cri) { // paging처리 return
	 * mapper.getListWithPaging(cri); }
	 * 
	 * @Override public ConquestVO getConquest(Long no) { return mapper.get(no); }
	 * 
	 * @Override public boolean deleteConquest(Long no) { return mapper.delete(no)
	 * == 1; }
	 * 
	 * @Override public boolean updateConquest(ConquestVO cvo) { return
	 * mapper.update(cvo) == 1; }
	 * 
	 * @Override public int getTotal(Ccriteria cri) { return
	 * mapper.getConquestCount(cri); }
	 */
}
