package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.member.MemberVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {
	
	@Autowired
	private MemberMapper mapper;
	
	//전체 회원 리스트
	@Test
	public void testGetList() {
		List<MemberVO> list = mapper.getList();
		assertNotEquals(list.size(), 0);
	}
	
	//회원 가입
	@Test
	public void testInsertSelectKey() {
		MemberVO member = new MemberVO();
		member.setId("hi200");
		member.setEmail("hyo@jin.com");
		member.setPassword("123");
		member.setName("hyojin00");
		member.setNickname("hyojiny00");
		member.setLoc("home");
		
		int before = mapper.getList().size();
		
		mapper.insertSelectKey(member);
		
		int after = mapper.getList().size();
		
		assertEquals(before+1, after);
	}
	
	//회원 정보 읽기 - 아이디
	@Test
	public void testReadMemberId() {
		MemberVO member = mapper.readMemberId("22");
		log.info(member);
	}
	
	//회원 정보 읽기 - 닉네임
	@Test
	public void testReadMemberNn() {
		MemberVO member = mapper.readMemberNn("1");
		log.info(member);
	}
	
	//회원 정보 읽기 - no
	@Test
	public void testRead() {
		MemberVO member = mapper.read(6L);
		log.info(member);
	}
	
	//회원 정보 수정
	@Test
	public void testUpdate() {
		MemberVO member = new MemberVO();
		member.setId("hi5");
		member.setEmail("kimhyo5@jin.com");
		member.setPassword("123");
		member.setName("kimhyojin");
		member.setNickname("kimhyojiny5");
		member.setLoc("myhome");
		
		mapper.insertSelectKey(member);
		
		member.setEmail("modifyhyo5@jin.com");
		member.setPassword("111");
		member.setNickname("modifykimhyojiny5");
		member.setLoc("modifymyhome");
		
		int cnt = mapper.update(member);
		//mapper.update()메소드에 영향을 받은 행의 갯수
		
		assertEquals(1, cnt);
		
		MemberVO updateMember = mapper.read(member.getNo());
		assertEquals("modifyhyo5@jin.com", updateMember.getEmail());
		assertEquals("111", updateMember.getPassword());
		assertEquals("modifykimhyojiny5", updateMember.getNickname());
		assertEquals("modifymyhome", updateMember.getLoc());
		
	}
	
	//회원 탈퇴(삭제)
	@Test
	public void testDelete() {
		int before = mapper.getList().size();
		
		int cnt = mapper.delete("hi8");
		//29번 삭제 테스트 완료 -> 29번 없음
		//받는 값(..?이 뭐였더라) 아이디로 바꾸고 테스트 완료
		assertEquals(1, cnt);
		
		int after = mapper.getList().size();
		
		assertEquals(before-1, after);
				
	}
	

}
