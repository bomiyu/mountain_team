package org.zerock.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.domain.mountain.MountainVO;
import org.zerock.mapper.MountainMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class MountainControllerTests {

	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	
	@Setter(onMethod_ =@Autowired)
	private MountainMapper mapper;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testExist() {
		assertNotNull(ctx);
		assertNotNull(mockMvc);
	}
	
	@Test
	public void testList() throws Exception{
		ModelAndView mv = mockMvc.perform(MockMvcRequestBuilders.get("/*/list"))
				.andReturn()
				.getModelAndView();
	}
	
	@Test
	public void testRegister() throws Exception {
	 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/register")
			 .param("mName", "관악산")
			 .param("mLoc", " 서울시 관악구, 금천구에서 경기도 안양시, 과천시까지 걸쳐있는 산")
			 .param("height", "632")
			 .param("status", "0"))
			 .andReturn();
		
	 log.info(result);
	 
	
	}
	
	@Test
	public void testGet() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/get")
				.param("no", "44"))
				.andReturn()
				.getModelAndView().getModelMap());
	}
	
	@Test
	public void testModify() throws Exception{
		MountainVO mountain = new MountainVO();
		mountain.setMName("산 이름 수정입니다.");
		mountain.setMLoc("산 수정된 위치입니다.");
		mountain.setHeight(350);
		mountain.setStatus(1);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/modify")
				.param("no", "44")
				.param("MName", "수락산 입니다.")
				.param("MLoc", "서울시 노원구에서 경기도 의정부시, 남양주시까지 걸쳐있는 산")
				.param("height", "638")
				.param("status", "0"))
				.andReturn();
	}

	@Test
	public void testRemove() throws Exception{
		// no--> 가져와서 삭제
		MvcResult resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/remove")
				.param("no", "49")
				).andReturn();
		
		log.info(resultPage);
	}
	
	

	
}
