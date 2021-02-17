package org.zerock.service.mountain;

import java.util.List;

import org.zerock.domain.mountain.MountainVO;

public interface MountainService {

	public void register(MountainVO mountain);
	
	public MountainVO get(Long no);
	
	public boolean modify(MountainVO mountain);
	
	public boolean remove(Long no);
	
	public List<MountainVO> getList();

	

	

	
}
