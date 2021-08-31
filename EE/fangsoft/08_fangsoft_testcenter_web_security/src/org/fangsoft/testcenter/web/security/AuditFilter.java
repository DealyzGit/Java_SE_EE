package org.fangsoft.testcenter.web.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AuditFilter implements Filter{


    public static final String PARAM_LOG_FILE = "fileName";//日志文件名
    public static final String PARAM_LIMIT = "limit";//日志文件大小上限
    public static final String PARAM_COUNT = "count";//日志文件的个数
    public static final String PARAM_APPEND = "append";//是否可以附加日志
    public static final String PARAM_LEVEL = "level";//日志级别
    private Logger auditLog;//日志对象

    private int str2Int(String val) {
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException ex) {
        }
        return 0;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        int limit = this.str2Int(filterConfig.getInitParameter(PARAM_LIMIT));
        limit = limit > 0 ? limit : 1000000;

        int count = this.str2Int(filterConfig.getInitParameter(PARAM_COUNT));
        count = count > 0 ? count : 2;
        boolean append = Boolean.parseBoolean(filterConfig.getInitParameter(PARAM_APPEND));

        Level level = str2Level(filterConfig.getInitParameter(PARAM_LEVEL));//init方法

        String logFileName = filterConfig.getInitParameter(PARAM_LOG_FILE);
        logFileName = logFileName == null ? "log/audit.log" : logFileName;
        logFileName = logFileName.startsWith("/") ? logFileName.substring(1) : logFileName;
        String webInfPath = filterConfig.getServletContext().getRealPath("/") + "/WEB-INF/";
        new File(webInfPath + getPath(logFileName)).mkdirs();
        auditLog = Logger.getLogger("audit");
        try {
            auditLog.addHandler(new FileHandler(webInfPath + logFileName, limit, count, append));
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        auditLog.setLevel(level);
        auditLog.setUseParentHandlers(false);//以上是init方法中代码

    }

    private Level str2Level(String val) {
        if (Level.ALL.getName().equals(val)) {
            return Level.ALL;
        } else if (Level.CONFIG.getName().equals(val)) {
            return Level.CONFIG;
        } else if (Level.FINE.getName().equals(val)) {
            return Level.FINE;
        } else if (Level.FINER.getName().equals(val)) {
            return Level.FINER;
        } else if (Level.FINEST.getName().equals(val)) {
            return Level.FINEST;
        } else if (Level.INFO.getName().equals(val)) {
            return Level.INFO;
        } else if (Level.OFF.getName().equals(val)) {
            return Level.OFF;
        } else if (Level.SEVERE.getName().equals(val)) {
            return Level.SEVERE;
        } else if (Level.WARNING.getName().equals(val)) {
            return Level.WARNING;
        }
        return Level.INFO;
    }

    private String getPath(String fileName) {
        int index = fileName.lastIndexOf("/");
        if (index > 0) {
            return fileName.substring(0, index);
        }
        return "";
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        Principal p = request.getUserPrincipal();
        String user = p == null ? "" : p.getName();
        long now = System.currentTimeMillis();
        auditLog.info(user + " visit " + request.getRequestURI() + " at:" +
                new Date(now));
        auditLog.info("client IP:" + request.getRemoteAddr());
        filterChain.doFilter(req, resp);
        auditLog.info("response " + request.getRequestURI() +
                " has spent:" + (System.currentTimeMillis() - now) +
                "(mill sconds)");
    }

    public void destroy() {
        this.auditLog=null;
    }


}
