package org.zerock.service.wish;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.wish.WishVO;

public interface WishService {

	public void register(WishVO wish); //찜 등록
	public List<WishVO> getWishList(); //전체 찜 리스트
	public List<WishVO> getMemberMem(Long member_no);  //찜 리스트 - 회원
	public List<WishVO> getMemberMt(Long mountain_no);  //찜 리스트 - 산 
	public boolean remove(@Param("member_no")Long member_no, @Param("mountain_no")Long mountain_no); //찜 삭제
	public int wishSize(Long mountain_no); //찜 개수
}
