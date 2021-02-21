package org.zerock.service.mountain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.mountain.ConqStickerVO;
import org.zerock.domain.mountain.MCriteria;
import org.zerock.domain.mountain.MnameVO;
import org.zerock.domain.mountain.MountainVO;
import org.zerock.mapper.MountainMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class MountainServiceImpl implements MountainService {
	
	@Setter(onMethod_ = @Autowired)
	private MountainMapper mapper;
	
	@Override
	public int getTotal(MCriteria mcri) {
		return mapper.getTotalCount(mcri);
	}
	
	@Override
	public void register(MountainVO mountain) {
		mapper.insertSelectKey(mountain);
	}

	@Override
	public MountainVO get(Long no) {
		return mapper.read(no) ;
	}

	@Override
	public boolean modify(MountainVO mountain) {
		return mapper.update(mountain) == 1;
	}	

	@Override
	public boolean remove(Long no) {
		return mapper.delete(no) == 1;
	}

	@Override
	public List<MountainVO> getList(MCriteria mcri) {
		return mapper.getListWithPaging(mcri);
	}
	
	// for CONQUEST table
	@Override
	public List<MnameVO> getMnameList() {
		return mapper.getMnameList();
	}
	
	@Override
	public List<ConqStickerVO> getConqListbyMem(Long user_no) {
		return mapper.getConqListbyMem(user_no);
	}
	
}
