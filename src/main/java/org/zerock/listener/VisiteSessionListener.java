package org.zerock.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zerock.mapper.VisitMapper;
import org.zerock.service.visit.VisitService;

/**
 * Application Lifecycle Listener implementation class VisiteSessionListener
 *
 */
//@WebListener
public class VisiteSessionListener implements HttpSessionListener {
	private VisitService service;
	
    /**
     * Default constructor. 
     */
    public VisiteSessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(se.getSession().getServletContext());
    	VisitMapper mapper = ctx.getBean(VisitMapper.class); // Bean을 주입
    	mapper.insert();
    	System.out.println("session created");
    }
    
    
    /*
	public void sessionCreated(HttpSessionEvent se)  {
		if(se.getSession().isNew()) {
		
		try {
			//전체 방문자 수 증가
			service.insert();
			
			int total = service.getTotal();
			int today = service.getToday();
			
			System.out.println("*****total ********" +total);
			System.out.println("*****today ********" +today);
			
			HttpSession session = se.getSession();
			session.setAttribute("totalCnt", total);
			session.setAttribute("todayCnt", today);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}
		

    }
    */

		

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
