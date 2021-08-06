package org.fangsoft.testcenter.web.framework;

import org.fangsoft.testcenter.web.Constants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class DispatcherServlet extends HttpServlet implements javax.servlet.Servlet {
    private static Map<String,ActionConfig> request2ActionMap;

    @SuppressWarnings("unchecked")

    public void init(ServletConfig config) throws ServletException {
        ServletContext context=config.getServletContext();
        request2ActionMap =(Map<String,ActionConfig>)context.getAttribute(Constants.APP_REQ_2_ACTION_CONFIG);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doProcess(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doProcess(request,response);
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestPath=request.getRequestURI();

        int index=requestPath.lastIndexOf("/");

        if(index!=-1)
            requestPath=requestPath.substring(index+1);

        ActionConfig actionConfig=request2ActionMap.get(requestPath);
        ResponsePage responsePage=actionConfig.getAction().doProcess(request, response, actionConfig);
        if(ResponsePage.SendMode.REDIRECT==responsePage.getMode()){
            response.sendRedirect(responsePage.getResponseURI());
        }else{
            request.getRequestDispatcher(responsePage.getResponseURI()).forward(request, response);
        }
    }

}
