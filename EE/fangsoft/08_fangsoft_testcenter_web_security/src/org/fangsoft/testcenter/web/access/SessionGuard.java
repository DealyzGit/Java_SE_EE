package org.fangsoft.testcenter.web.access;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionGuard implements HttpSessionListener, ServletContextListener {


    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
        context.setAttribute(SessionGuardBean.APP_SESSION_GUARD,
                new SessionGuardBean());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
        context.removeAttribute(SessionGuardBean.APP_SESSION_GUARD);
    }

    private SessionGuardBean getSessionGuardBean(HttpSessionEvent se) {
        HttpSession session=se.getSession();
        ServletContext context=session.getServletContext();
        SessionGuardBean countBean=(SessionGuardBean)context.
                getAttribute(SessionGuardBean.APP_SESSION_GUARD);
        return countBean;
    }
    public void sessionCreated(HttpSessionEvent se) {
        SessionGuardBean countBean = getSessionGuardBean(se);
        countBean.add();
    }
    public void sessionDestroyed(HttpSessionEvent se) {
        SessionGuardBean countBean = getSessionGuardBean(se);
        countBean.decrease();
    }
    
}
