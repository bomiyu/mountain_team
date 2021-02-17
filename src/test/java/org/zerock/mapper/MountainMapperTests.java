package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.mountain.MountainVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class MountainMapperTests {

	@Setter(onMethod_ =@Autowired )
	private MountainMapper mapper;

	
	@Test
	public void testGetList() {
		mapper.getList().forEach(mountain -> log.info(mountain));
	}
	
	@Test
	public void testInsert() {
		MountainVO mountain = new MountainVO();
		mountain.setMName("도봉산 입니다.");
		mountain.setMLoc("산 위치 입니다.");
		mountain.setHeight(1300);
		mountain.setStatus(0);
		
		mapper.insert(mountain);
		
		log.info(mountain);
		
		
	}

	@Test
	public void testInsertSelectKey() {
		MountainVO mountain = new MountainVO();
		mountain.setMName("아차산 입니다.");
		mountain.setMLoc("산 위치 입니다.");
		mountain.setHeight(300);
		mountain.setStatus(0);

		mapper.insertSelectKey(mountain);

		log.info(mountain);
	}
	
	// 현재 테이블에 no값이 있는지 확인
	@Test
	public void testRead() {
		MountainVO mountain = mapper.read(61L);
		
		log.info(mountain);
	}
	
	@Test
	public void testDelete() {
		
		log.info(mapper.delete(1L));
		
	}
	
	@Test
	public void testUpdate() {
	MountainVO mountain = new MountainVO();
	mountain.setMName("산 이름 수정입니다.");
	mountain.setMLoc("산 수정된 위치입니다.");
	mountain.setHeight(350);
	mountain.setStatus(1);
	mountain.setNo(3L);
	
	
	
	int count = mapper.update(mountain);
	log.info("UPDATE COUNT:" + count);
	}

}
