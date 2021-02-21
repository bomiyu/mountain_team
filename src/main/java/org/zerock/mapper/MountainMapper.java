package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.mountain.ConqStickerVO;
import org.zerock.domain.mountain.MCriteria;
import org.zerock.domain.mountain.MnameVO;
import org.zerock.domain.mountain.MountainVO;

public interface MountainMapper {

	public int getTotalCount(MCriteria mcri);
	
	public void insertSelectKey(MountainVO mountain);

	public MountainVO read(Long no);

	public int update(MountainVO mountain);

	public int delete(Long no);

	public List<MountainVO> getList();

	public List<MountainVO> getListWithPaging(MCriteria mcri);

	// for CONQUEST table
	public List<MnameVO> getMnameList();
	
	public List<ConqStickerVO> getConqListbyMem(Long user_no);// user_no == authUser.no
}
