package org.fangsoft.testcenter.web;

import org.fangsoft.testcenter.web.action.ActionConfigMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MVCFrameworkConfigListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc=sce.getServletContext();
        sc.removeAttribute(Constants.APP_REQ_2_ACTION_CONFIG);
    }
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc=sce.getServletContext();
        sc.setAttribute(Constants.APP_REQ_2_ACTION_CONFIG,
                ActionConfigMapping.getRequest2ActionMap());

    }
}
