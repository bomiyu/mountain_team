package org.zerock.service.member;

import org.springframework.stereotype.Service;
import org.zerock.domain.member.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service //서비스 bean 객체 생성
@AllArgsConstructor	//생성자 생성
@Log4j
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper mapper;
	
	//##회원 등록
	@Override
	public void register(MemberVO member) {
		mapper.insertSelectKey(member);
	}
	
	//##회원 정보 읽기 - 아이디
	@Override
	public MemberVO getMemberId(String id) {
		return mapper.readMemberId(id);
	}

	//##회원 정보 읽기 - 닉네임
	@Override
	public MemberVO getMemberNn(String nn) {
		return mapper.readMemberNn(nn);
	}
	
	//##회원 정보 읽기 - no
	//나중에도 사용하지않으면 지울 수 있음!
	//사용할 시 사용 여부 표시바람
	@Override
	public MemberVO get(Long no) {
		return mapper.read(no);
	}
	
	//##회원 정보 수정
	@Override
	public boolean modify(MemberVO member) {
		return mapper.update(member) == 1;
	}

	//##회원 탈퇴(삭제)
	@Override
	public boolean remove(String id) {
		return mapper.delete(id) == 1;
	}
	
	
	//##회원 정보 체크
	@Override
	public boolean checkMember(String memA, String memB) {
			return memA.equals(memB);
	}
}