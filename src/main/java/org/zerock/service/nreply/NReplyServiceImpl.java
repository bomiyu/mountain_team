package org.zerock.service.nreply;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.nreply.NReplyVO;
import org.zerock.mapper.NReplyMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NReplyServiceImpl implements NReplyService {
	
	private NReplyMapper mapper;
	
	@Override
	public int getTotal(Long notice_no) {
		return mapper.getTotalCount(notice_no);
	}
	
	@Override
	public int register(NReplyVO reply) {
		return mapper.insertSelectKey(reply);
	}
	
	@Override
	public NReplyVO get(Long no) {
		return mapper.read(no);
	}
	
	@Override
	public boolean modify(NReplyVO reply) {
		return mapper.update(reply) == 1;
	}
	
	@Override
	public boolean remove(Long no) {
		return mapper.delete(no) == 1;
	}
	
	@Override
	public List<NReplyVO> getList(Long notice_no) {
		return mapper.getList(notice_no);
	}
}
