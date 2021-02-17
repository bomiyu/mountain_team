package org.zerock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.wish.WishVO;
import org.zerock.mapper.WishMapper;
import org.zerock.service.wish.WishService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class WishServiceTests {

	@Autowired
	private WishService service;
	
	@Autowired
	private WishMapper mapper;
	
	//찜 리스트
	@Test
	public void testGetWishList() {
		List<WishVO> list = service.getWishList();
		assertEquals(list.size(), 5);
		
	}
	
	//찜 등록
	@Test
	public void testRegister() {
		WishVO wish = new WishVO();
		wish.setMember_no(77L);
		wish.setMountain_no(7L);

		
		int before = mapper.getList().size();
		
		service.register(wish);
		
		log.info("여기까지1");
		int after = mapper.getList().size();
		log.info("여기까지2");
		
		assertEquals(before+1, after);
		log.info("여기까지3");
		
	}
	
	//찜 리스트 - 회원
	@Test
	public void testGetMemberMem() {
		List<WishVO> wish = service.getMemberMem(10L);
		
		log.info(wish);
		
	}

	//찜 리스트 - 산
	@Test
	public void testGetMemberMt() {
		List<WishVO> wish = service.getMemberMt(10L);
		
		log.info(wish);
		
	}
	
	//찜 삭제
	@Test
	public void testRemove() {
		assertTrue(service.remove(10L, 1L));
	}
	
}
