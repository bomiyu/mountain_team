package org.zerock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.member.MemberVO;
import org.zerock.mapper.MemberMapper;
import org.zerock.service.member.MemberService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTests {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private MemberMapper mapper;
	
	//회원 등록
	@Test
	public void testRegister() {
		MemberVO member = new MemberVO();
		member.setId("hi5");
		member.setEmail("kimhyo5@jin.com");
		member.setPassword("123");
		member.setName("kimhyojin");
		member.setNickname("kimhyojiny5");
		member.setLoc("myhome");
		
		int before = mapper.getList().size();
		
		service.register(member);
		
		int after = mapper.getList().size();
		
		assertEquals(before+1, after);
		
	}
	
	//회원 정보 읽기 - 아이디 
	@Test
	public void testGetMemberId() {
		MemberVO member = service.getMemberId("coco");
		
		log.info(member);
		
	}

	//회원 정보 읽기 - 닉네임 
	@Test
	public void testGetMemberNn() {
		MemberVO member = service.getMemberNn("1");
		
		log.info(member);
		
	}
	
	//회원 정보 읽기 - no
	@Test
	public void testGet() {
		MemberVO member = service.get(6L);
		
		log.info(member);
		
	}
	
	//회원 정보 수정
	@Test
	public void testModify() {
		MemberVO member = new MemberVO();
		member.setId("hi9");
		member.setEmail("kimhyo9@jin.com");
		member.setPassword("123");
		member.setName("kimhyojin");
		member.setNickname("kimhyojiny9");
		member.setLoc("myhome");
		
		service.register(member);
		
		MemberVO modifyMember = new MemberVO();
		modifyMember.setNo(member.getNo());
		modifyMember.setEmail("modifyhyo9@jin.com");
		modifyMember.setPassword("111");
		modifyMember.setNickname("modifykimhyojiny9");
		modifyMember.setLoc("modifymyhome");
		
		assertTrue(service.modify(modifyMember));
		
		MemberVO member2 = service.get(member.getNo());
		
		assertEquals("modifyhyo9@jin.com", member2.getEmail());
		assertEquals("111", member2.getPassword());
		assertEquals("modifykimhyojiny9", member2.getNickname());
		assertEquals("modifymyhome", member2.getLoc());

	}
	
	//회원 탈퇴(삭제)
	@Test
	public void testRemove() {
		assertTrue(service.remove("hi8"));
	}
	
	
	
	
	
	
}