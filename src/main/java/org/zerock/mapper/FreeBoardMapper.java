package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.freeboard.FCriteria;
import org.zerock.domain.freeboard.FreeBoardVO;

public interface FreeBoardMapper {

	public int getTotalCount(FCriteria cri); // SELECT count(*) FROM FreeBoard

	public List<FreeBoardVO> getList();

	public void insertSelectKey(FreeBoardVO vo);

	public FreeBoardVO get(Long no);

	public int delete(Long no);

	public List<FreeBoardVO> getListWithPaging(FCriteria cri);

	public int update(FreeBoardVO vo);

}
