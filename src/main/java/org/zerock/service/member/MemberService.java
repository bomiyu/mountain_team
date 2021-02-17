package org.zerock.service.member;

import org.zerock.domain.member.MemberVO;

public interface MemberService {
	
	public void register(MemberVO member); //회원 등록
	public MemberVO getMemberId(String id);  //회원 정보 읽기 - 아이디 
	public MemberVO getMemberNn(String nn);  //회원 정보 읽기 - 아이디 
	public MemberVO get(Long no); //회원 정보 읽기 - no
	public boolean modify(MemberVO member); //회원 정보 수정
	public boolean remove(String id); //회원 탈퇴(삭제) - 아이디
	
	public boolean checkMember(String memA, String memB); //회원 정보 체크
	
	
}	