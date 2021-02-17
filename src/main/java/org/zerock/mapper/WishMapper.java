package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.wish.WishVO;

public interface WishMapper {
	public void insertSelectKey(WishVO wish); //찜 등록
	public List<WishVO> getList(); //전체 찜 리스트
	public List<WishVO> readWishMem(Long member_no); //찜 리스트 - 회원
	public List<WishVO> readWishMt(Long mountain_no); //찜 리스트 - 산
	public int delete(@Param("member_no")Long member_no, @Param("mountain_no")Long mountain_no); //찜 삭제
	//보내는 parameter가 두개 이상일 경우 @Param 어노테잇녀을 써줘야 날라감.
	public int wishSize(Long member_no); //찜 개수
}
