package org.zerock.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MountainListener
 *
 */
public class MountainListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MountainListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	// Context Root -> Application 영역에 추가
    	ServletContext application = sce.getServletContext();
    	application.setAttribute("root", application.getContextPath());
    	
    	application.setAttribute("staticPath", "https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnkghg76rfem/b/bucket-20210216-1420/o");
    }
	
}
