package org.fangsoft.testcenter.web.action;

import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.framework.ActionConfig;
import org.fangsoft.testcenter.web.framework.ResponsePage;
import org.fangsoft.util.DataConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StartTestAction extends TestCenterAction{
    public ResponsePage doProcess(HttpServletRequest request, HttpServletResponse response, ActionConfig actionConfig) throws ServletException, IOException {
        if(!this.isLogined(request))return this.processNotLogin(actionConfig);
        HttpSession session=request.getSession(false);
        TestResult testResult=null;
        if(session.getAttribute(Constants.SESSION_TESTRESULT)!=null){
            testResult=(TestResult)session.getAttribute(Constants.SESSION_TESTRESULT);
        }else{
            int testId= DataConverter.str2Int(request.getParameter("testId"));
            int testReservationId=DataConverter.str2Int(request.getParameter("testReservationId"));
            testResult=this.getTestCenterFacade().
                    startTest(testId, testReservationId,getCustomer(request));
            session.setAttribute(Constants.SESSION_TESTRESULT, testResult);
            session.setAttribute(Constants.SESSION_TEST_RESERVATION,
                    testReservationId);
        }
        session.setMaxInactiveInterval(this.getTestCenterFacade().getRemainingTestTime(testResult)+30);
        return actionConfig.getResponsePage(SUCCESS);
    }

}
