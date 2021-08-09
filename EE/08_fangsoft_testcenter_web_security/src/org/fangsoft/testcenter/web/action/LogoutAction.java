package org.fangsoft.testcenter.web.action;

import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.framework.ActionConfig;
import org.fangsoft.testcenter.web.framework.ResponsePage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutAction extends TestCenterAction{

    public ResponsePage doProcess(HttpServletRequest request, HttpServletResponse response, ActionConfig actionConfig)
            throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        if(session!=null){
            if(session.getAttribute(Constants.SESSION_TESTRESULT)!=null &&
                    session.getAttribute(Constants.SESSION_TEST_RESERVATION)!=null){
                TestResult testResult=(TestResult)session.
                        getAttribute(Constants.SESSION_TESTRESULT);
                int testReservationId=(Integer)session.
                        getAttribute(Constants.SESSION_TEST_RESERVATION);
                this.getTestCenterFacade().
                        commitTest(testResult, testReservationId);
            }
            session.invalidate();
        }
        return actionConfig.getResponsePage(SUCCESS);
    }

}
