package org.zerock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

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
import org.zerock.mapper.MountainMapper;
import org.zerock.service.mountain.MountainService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MountainServiceTests {

	@Setter(onMethod_ = @Autowired)
	private MountainService service;

	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testRegiter() {
		MountainVO mountain = new MountainVO();
	    mountain.setMname("서울에 있는 산인뎁쇼");
	    mountain.setMloc("서울시 도봉구에서 시작되어있는 산");
	    mountain.setHeight(640);
	    mountain.setStatus(0);
	    mountain.setDescription("설명");
	    
	    int before = service.getTotal(null);
	    
	    service.register(mountain);
	    
	    int after = service.getTotal(null);
	    
	    assertEquals(after, before + 1);
	    log.info("새로 등록된 산 정보는: "+ mountain.getNo());
	}
	
	@Test
	public void testGet() {
		MountainVO mountain = new MountainVO();
		mountain.setMname("새로 입력하는 산");
		mountain.setMloc("seoul");
		mountain.setHeight(180);
		mountain.setStatus(0);
		mountain.setDescription("설명");
		
		service.register(mountain);
		
		MountainVO m = service.get(mountain.getNo());
		
		log.info(m);
	}
	
    @Test
    public void testUpdate() {
    	MountainVO mountain = new MountainVO();
    	mountain.setMname("헤벨레산입니다요");
    	mountain.setMloc("서울시 마포구");
    	mountain.setHeight(903);
    	mountain.setStatus(0);
    	mountain.setDescription("설명");
    	
    	service.register(mountain);
    	
    	MountainVO update = new MountainVO();
    	update.setNo(mountain.getNo());
    	update.setMname("헤벌레벌쭉산");
    	update.setMloc("서울시 마포구에서 시작되는 산");
    	update.setHeight(833);
    	update.setStatus(0);
    	mountain.setDescription("설명2");
    	
    	service.modify(update);
    	
    	assertEquals(mountain.getNo(), update.getNo());
    	log.info("MODIFY RESULT: "+ service.modify(mountain));
    }
 
	@Test
	public void testDelete() {
		MountainVO mountain = new MountainVO();
	    mountain.setMname("서울산333333");
	    mountain.setMloc("서울시 도봉구에서 시작되어있는 산");
	    mountain.setHeight(640);
	    mountain.setStatus(0);
	    mountain.setDescription("설명");
	   
	    service.register(mountain);
	    
	    int before = service.getTotal(null);
	    
		service.remove(mountain.getNo());
		
		int after = service.getTotal(null);
		
		assertEquals(after, before - 1);
	}
    
	@Test
	public void testGetList() {
		List<MountainVO> list = service.getList(new MCriteria());
		log.info(list);
	}
	
	
	// for CONQUEST table
	@Test
	public void testGetMnameList() {
		List<MnameVO> list = service.getMnameList();
		log.info(list);
	}
	
	@Test
	public void testGetConqListbyMem() {
		List<ConqStickerVO> list = service.getConqListbyMem(245L);
		log.info(list);
	}
	
}
