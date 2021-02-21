package org.zerock.controller;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

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
		ModelAndView mv = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView();
		
		Map<String, Object> model = mv.getModel();
		Object o = model.get("list");
		
		String viewName = mv.getViewName();
		
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
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
				.param("no", "44"))
				.andReturn();
		
		String viewName = result.getModelAndView().getViewName();
		Map<String, Object> modelMap = result.getModelAndView().getModel();
				
		/*log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
				.param("no", "44"))
				.andReturn()
				.getModelAndView().getModelMap());*/
	}
	
	@Test
	public void testModify() throws Exception{
		MountainVO mountain = new MountainVO();
		mountain.setMname("이름");
		mountain.setMloc("서울시 동작구");
		mountain.setHeight(480);
		mountain.setStatus(0);
		
		mapper.insertSelectKey(mountain);
		
		Long key = mountain.getNo();
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
			    .param("no", key +"")
			    .param("MName","인왕산")
			    .param("MLoc", "서울 종로구와 서대문구 홍제동 경계에 있는 산")
			    .param("height", "338.2")
			    .param("status", "0"))
				.andReturn();
		
		   
				/*
				.param("no", "44")
				.param("MName", "수락산 입니다.")
				.param("MLoc", "서울시 노원구에서 경기도 의정부시, 남양주시까지 걸쳐있는 산")
				.param("height", "638")
				.param("status", "0"))
				.andReturn();
				*/
	}

	@Test
	public void testRemove() throws Exception{
		// no--> 가져와서 삭제
		/*
		MountainVO mountain = new MountainVO();
		
		mountain.setMName("이름");
		mountain.setMLoc("서울시 동작구");
		mountain.setHeight(480);
		mountain.setStatus(0);
		
		mapper.insertSelectKey(mountain);
		
		Long key = mountain.getNo();
		
		int before = mapper.getList().size();
		*/
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("no", "284"))
				.andReturn()
		        .getModelAndView().getViewName();
		
		int after= mapper.getList().size();
		
		log.info(resultPage);
		
		
	}
	
	


}
