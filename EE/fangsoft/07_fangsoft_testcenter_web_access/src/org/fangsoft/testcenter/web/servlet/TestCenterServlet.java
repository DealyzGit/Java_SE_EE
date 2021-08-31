package org.fangsoft.testcenter.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.fangsoft.testcenter.business.TestCenterFacade;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.URLConfig;

import java.io.IOException;

@WebServlet(name = "TestCenterServlet", value = "/TestCenterServlet")
public class TestCenterServlet extends HttpServlet implements Servlet {
    private TestCenterFacade testCenterFacade=TestCenterFacade.getInstance();
    public TestCenterServlet() {}
    protected TestCenterFacade getTestCenterFacade() {
        return testCenterFacade;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doProcess(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doProcess(request, response);
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("TestCenterServlet output");
    }
    protected Customer getCustomer(HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        return (Customer)session.getAttribute(Constants.SESSION_USERID);
    }
    protected boolean isLogined(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        if(session!=null){
            if(session.getAttribute(Constants.SESSION_USERID)!=null){
                return true;
            }else{
                session.invalidate();
            }
        }
        return false;
    }
    protected void processNotLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(URLConfig.urlLoginView);
    }

}

