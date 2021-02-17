package org.zerock.mapper;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.wish.WishVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class WishMapperTests {
	
	@Autowired
	private WishMapper mapper;
	
	//전체 찜 리스트
	@Test
	public void testGetList() {
		List<WishVO> list = mapper.getList();
		assertEquals(list.size(), 0);
	}
	
	//찜 등록
	@Test
	public void testInsertSelectKey() {
		WishVO wish = new WishVO();
		wish.setMember_no(555L);
		wish.setMountain_no(5L);
		
		int before = mapper.getList().size();
		
		mapper.insertSelectKey(wish);
		
		int after = mapper.getList().size();
		
		assertEquals(before+1, after);
	}
	
	//찜 리스트 - 회원
	@Test
	public void testreadWishMem() {
		List<WishVO> wish = mapper.readWishMem(222L);
		log.info(wish);
	}
	
	//찜 리스트 - 산
	@Test
	public void testreadWishMt() {
		List<WishVO> wish = mapper.readWishMt(5L);
		log.info(wish);
	}
	
	//찜 삭제
	@Test
	public void testDelete() {
		int before = mapper.getList().size();
		
		int cnt = mapper.delete(555L, 5L);
		assertEquals(1, cnt);
		
		int after = mapper.getList().size();
		
		assertEquals(before-1, after);
				
	}
	
	//찜 개수
	@Test
	public void testWishSize() {
		int size = mapper.wishSize(77L);
		assertEquals(size, 1);
		
		
	}
	
}