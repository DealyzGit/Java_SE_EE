package org.fangsoft.testcenter.web.action;

import org.fangsoft.testcenter.business.TestCenterFacade;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.framework.Action;
import org.fangsoft.testcenter.web.framework.ActionConfig;
import org.fangsoft.testcenter.web.framework.ResponsePage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TestCenterAction implements Action {
    protected static final String SUCCESS = "success";
    protected static final String FAILURE = "failure";

    public ResponsePage doProcess(HttpServletRequest request,
                                  HttpServletResponse response, ActionConfig urlMap)
            throws ServletException, IOException {
        return null;
    }

    protected TestCenterFacade getTestCenterFacade(){
        return TestCenterFacade.getInstance();
    }

    public Customer getCustomer(HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        return  (Customer)session.
                getAttribute(Constants.SESSION_USERID);
    }

    protected boolean isLogined(HttpServletRequest request){
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
    public ResponsePage  processNotLogin(ActionConfig actionConfig)
            throws ServletException, IOException {
        return actionConfig.getResponsePage("notLogined");
    }

}
