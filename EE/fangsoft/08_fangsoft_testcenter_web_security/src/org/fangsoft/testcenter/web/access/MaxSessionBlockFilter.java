package org.fangsoft.testcenter.web.access;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MaxSessionBlockFilter implements Filter {
    public static final int MAX_SESSIONS = 2;//默认允许的最大会话数
    public static final String MAX_SESSIONS_PARAM = "maxSessions";
    public static final String SERVER_BUSY_URL_PARAM = "serverBusyURL";
    private int maxSessions;
    private String serverBusyURL;
    private FilterConfig filterConfig;

    private int validateMaxSession(String maxSessions) {
        if (maxSessions != null) {
            try {
                return Integer.parseInt(maxSessions);
            } catch (NumberFormatException ex) {
            }
        }
        return MAX_SESSIONS;
    }

    private String validateURL(String url) {
        return url.startsWith("/") ? url : "/" + url;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String maxSessions = filterConfig.getInitParameter(MAX_SESSIONS_PARAM);
        this.maxSessions = this.validateMaxSession(maxSessions);
        String busyURL = filterConfig.getInitParameter(SERVER_BUSY_URL_PARAM);
        this.serverBusyURL = this.validateURL(busyURL);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletContext context = this.filterConfig.getServletContext();
        SessionGuardBean count = (SessionGuardBean) context.getAttribute(SessionGuardBean.APP_SESSION_GUARD);
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (count.getCount() >= this.maxSessions && httpRequest.getSession(false) == null) {
            httpRequest.getRequestDispatcher(this.serverBusyURL).forward(httpRequest, httpResponse);
            return;
        } else {
            chain.doFilter(request, response);
        }
    }
    public void destroy() {
        this.filterConfig=null;
//        this.requireSessionRequest=null;
    }


}


