package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.restaurant.LikeVO;

public interface LikeMapper {
	
	public int insertLike(LikeVO like);
	
	public int getCount(Long resno);
	
//	public int dislikeCount(Long resno);
// 파라미터가 1개 일 때 컴파일 시 'param1' 2개 이상 파라미터값이 필요할 때 @Param으로 원하는 명으로 적용됨.
	public int deleteLike(@Param("userno") Long userno, @Param("resno") Long resno);
	
	public int updateLike(Long resno);
	
	public int getLike(@Param("userno") Long userno, @Param("resno") Long resno);
	
	public int getDislike(@Param("userno") Long userno, @Param("resno") Long resno);
	
}