package org.zerock.service.wish;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.zerock.domain.wish.WishVO;
import org.zerock.mapper.WishMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service //서비스 bean 객체 생성
@AllArgsConstructor	//생성자 생성
@Log4j
public class WishServiceImpl implements WishService{

	private WishMapper mapper;
	
	@Override
	public void register(WishVO wish) {
		mapper.insertSelectKey(wish);
	}
	
	@Override
	public List<WishVO> getWishList() {
		return mapper.getList();
	}
	
	@Override
	public List<WishVO> getMemberMem(Long member_no) {
		return mapper.readWishMem(member_no);
	}
	
	@Override
	public List<WishVO> getMemberMt(Long mountain_no) {
		return mapper.readWishMt(mountain_no);
	}
	
	@Override
	public boolean remove(@Param("member_no")Long member_no, @Param("mountain_no")Long mountain_no) {
		return mapper.delete(member_no, mountain_no) == 1;
	}
	
	@Override
	public int wishSize(Long member_no) {
		return mapper.wishSize(member_no);
		
	}
}