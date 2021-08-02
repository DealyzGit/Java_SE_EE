package org.fangsoft.testcenter.web.servlet;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.URLConfig;
import org.fangsoft.util.DataConverter;
import org.fangsoft.view.StartTestView;

import java.io.IOException;

@WebServlet(name = "StartTestServlet", value = "/startTest")
public class StartTestServlet extends TestCenterServlet implements Servlet {

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!this.isLogined(request, response)) {
            this.processNotLogin(request, response);
            return;//必须在此return，已在processNotLogin中重定向请求
        }
        HttpSession session = request.getSession(false);
        TestResult testResult = null;

        if (session.getAttribute(Constants.SESSION_TESTRESULT) != null) {
            testResult = (TestResult) session.getAttribute(Constants.SESSION_TESTRESULT);

            session.setMaxInactiveInterval(this.getTestCenterFacade().getRemainingTestTime(testResult) + 3000);

            int testId = DataConverter.str2Int(request.getParameter("testId"));
            int testReservationId = DataConverter.str2Int(request.getParameter("testReservationId"));
            testResult = this.getTestCenterFacade().startTest(testId, testReservationId, testResult.getCustomer());

            session.setAttribute(Constants.SESSION_TESTRESULT, testResult);
            session.setAttribute(Constants.SESSION_TEST_RESERVATION, testReservationId);

        } else {
            int testId = DataConverter.str2Int(request.getParameter("testId"));
            int testReservationId = DataConverter.str2Int(request.getParameter("testReservationId"));
            testResult = this.getTestCenterFacade().startTest(testId, testReservationId, this.getCustomer(request));

            testResult = this.getTestCenterFacade().startTest(testId, testReservationId, testResult.getCustomer());

            session.setAttribute(Constants.SESSION_TESTRESULT, testResult);
            session.setAttribute(Constants.SESSION_TEST_RESERVATION, testReservationId);
        }
        displayView(request, response, testResult.getTest());
    }

    private void displayView(HttpServletRequest request, HttpServletResponse response, Test test) throws IOException {

        StartTestView view = new StartTestView();

        view.setTest(test);
        view.setUrlCommitTestAction(URLConfig.urlCommitTestAction);
        view.setUserId(this.getCustomer(request).getUserId());
        view.display(response);


    }
}

