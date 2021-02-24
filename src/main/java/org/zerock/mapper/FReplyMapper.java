package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.freeboard.FCriteria;
import org.zerock.domain.freply.FReplyVO;


public interface FReplyMapper {
	public int insertSelectKey(FReplyVO vo);
	public FReplyVO read(Long no);
	public int delete(Long no);
	public int update(FReplyVO reply);
	public List<FReplyVO> getListWithPaging(
			@Param("fcri") FCriteria fcri,
			@Param("board_no") Long board_no);
}
