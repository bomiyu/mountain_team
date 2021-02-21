package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.mountain.ConqStickerVO;
import org.zerock.domain.mountain.MCriteria;
import org.zerock.domain.mountain.MnameVO;
import org.zerock.domain.mountain.MountainVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MountainMapperTests {

	@Setter(onMethod_ = @Autowired)
	private MountainMapper mapper;

	@Test
	public void testInsertSelectKey() {
		MountainVO mountain = new MountainVO();
		mountain.setMname("산20");
		mountain.setMloc("서울특별시");
		mountain.setHeight(240);
		mountain.setStatus(0);
		mountain.setDescription("설명");

		int before = mapper.getTotalCount(new MCriteria());

		mapper.insertSelectKey(mountain);

		int after = mapper.getTotalCount(new MCriteria());

		assertEquals(after, before + 1);
	}

	// 현재 테이블에 no값이 있는지 확인
	// 무결성 때문에 새로운값 입력
	@Test
	public void testRead() {
		MountainVO mountain = new MountainVO();
		mountain.setMname("산2");
		mountain.setMloc("서울");
		mountain.setHeight(667);
		mountain.setStatus(0);
		mountain.setDescription("설명");
		
		mapper.insertSelectKey(mountain);

		MountainVO readMountain = mapper.read(mountain.getNo());

		assertEquals(readMountain.getNo(), mountain.getNo());

		log.info(mountain);
	}

	@Test
	public void testUpdate() {
		MountainVO mountain = new MountainVO();

		mountain.setMname("산이름이걸랑요");
		mountain.setMloc("서울시 관악구입니다");
		mountain.setHeight(500);
		mountain.setStatus(1);
		mountain.setDescription("설명");

		mapper.insertSelectKey(mountain);

		mountain.setMname("변경산");
		
		int cnt = mapper.update(mountain);

		assertEquals(1, cnt);
		mapper.read(mountain.getNo());
	}

	@Test
	public void testDelete() {
		MountainVO mountain = new MountainVO();
		mountain.setMname("새로운 산입니다3");
		mountain.setMloc("서울시 ");
		mountain.setHeight(240);
		mountain.setStatus(0);
		mountain.setDescription("설명");

		mapper.insertSelectKey(mountain);

		int before = mapper.getTotalCount(null);

		assertEquals(1, mapper.delete(mountain.getNo()));

		int after = mapper.getTotalCount(null);

		assertEquals(after, before - 1);
	}

	@Test
	public void testGetList() {
		List<MountainVO> list = mapper.getList();
		assertNotEquals(list.size(), 0);
	}

	@Test
	public void testGetListWithPaging() {
		List<MountainVO> list = mapper.getListWithPaging(new MCriteria());
		log.info(list);
		
		//list.forEach(mountain -> log.info("번호:" + mountain.getNo()));
	}
	
	
	// for CONQUEST table
	@Test
	public void testGetMnameList() {
		List<MnameVO> list = mapper.getMnameList();
		log.info(list);
	}
	
	@Test
	public void testGetConqListbyMem() {
		List<ConqStickerVO> list = mapper.getConqListbyMem(245L);
		log.info(list);
	}
}
