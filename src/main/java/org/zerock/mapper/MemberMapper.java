package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.member.MemberVO;

public interface MemberMapper {

	public void insertSelectKey(MemberVO member); //회원 가입
	public List<MemberVO> getList();	//전체 회원 리스트
	public MemberVO readMemberId(String id); //회원 정보 읽기 - 아이디
	public MemberVO readMemberNn(String nn); //회원 정보 읽기 - 닉네임
	public MemberVO read(Long no); //회원 정보 읽기 - no
	public int update(MemberVO member); //회원 정보 수정
	public int delete(String id); //회원 탈퇴(삭제) - 아이디
	
}