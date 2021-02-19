package org.zerock.mapper;


import org.zerock.domain.conquest.ConquestVO;


public interface ConquestMapper {
//	public int getConquestCount(); // SELECT count(*) FROM Conquest

	public int addConquest(ConquestVO cvo);
	
//	public List<ConquestVO> getList();
//	public ConquestVO get(Long no);
//
//	public int delete(Long no);
//
//	public int update(ConquestVO vo);
}
