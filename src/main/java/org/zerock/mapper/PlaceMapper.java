package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.place.Pcriteria;
import org.zerock.domain.place.PlaceVO;


public interface PlaceMapper {
	
	public void insertSelectKey(PlaceVO place);
	
	public List<PlaceVO> getList();
	
	public List<PlaceVO> getListPaging(Pcriteria cri);
	
	public int delete(Long no);
	
	public int update(PlaceVO place);
	
	public PlaceVO read(Long no);
	
	public int getTotalCnt(Pcriteria cri);
	
	public int deleteLikeResno(Long no);
	
}
