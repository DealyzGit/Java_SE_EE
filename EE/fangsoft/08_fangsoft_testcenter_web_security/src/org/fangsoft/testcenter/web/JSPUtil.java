package org.fangsoft.testcenter.web;

//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;

import org.fangsoft.testcenter.business.TestCenterFacade;
import org.fangsoft.testcenter.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class JSPUtil {
    public static Customer getCustomer(HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        return (Customer)session.getAttribute(Constants.SESSION_USERID);
    }

    public static TestCenterFacade getTestCenterFacade() {
        return TestCenterFacade.getInstance();
    }

    public static String makeLink(String url, String text) {
        return "<a href=\"" + url + "\">" + text + "</a>";
    }

    public static boolean processNotLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        if(session!=null){
            if(session.getAttribute(Constants.SESSION_USERID)!=null){
                return true;
            }else{
                session.invalidate();
            }
        }
        response.reset();
        try {
            response.sendRedirect(URLConfig.urlLoginView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
