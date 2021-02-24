package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.admin.Acriteria;
import org.zerock.domain.admin.AdminMemberVO;

public interface AdminMemberMapper {
	
	public List<AdminMemberVO> getMemberListPaging(Acriteria cri);
	
	public AdminMemberVO getMember(Long no);
	
	public int getMemberTotalCnt(Acriteria cri);

	public int getMemberCnt();

	public List<AdminMemberVO> getAdminListPaging(Acriteria cri);
	
	public int getAdminCnt();
	
	public int managerChange(Long no);
	
	public int generalMemberChange(Long no);
}
