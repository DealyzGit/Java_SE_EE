package org.fangsoft.testcenter.web;

//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.HttpSessionEvent;
//import jakarta.servlet.http.HttpSessionListener;

import org.fangsoft.testcenter.business.TestCenterFacade;
import org.fangsoft.testcenter.model.TestResult;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class TestTimeoutGuard implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent hse) {}
    public void sessionDestroyed(HttpSessionEvent hse) {
        HttpSession session=hse.getSession();
        if(session.getAttribute(Constants.SESSION_TESTRESULT)!=null){
            TestResult testResult=(TestResult)session.
                    getAttribute(Constants.SESSION_TESTRESULT);
            int testReservationId=(Integer)session.
                    getAttribute(Constants.SESSION_TEST_RESERVATION);
            TestCenterFacade.getInstance().
                    commitTest(testResult, testReservationId);
        }
    }
}
