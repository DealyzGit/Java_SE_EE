package org.fangsoft.testcenter.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.view.TestResultView;

import java.io.IOException;

@WebServlet(name = "DisplayTestResultViewServlet", value = "/testResultView")
public class DisplayTestResultViewServlet extends TestCenterServlet {


    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!this.isLogined(request, response)) {
            this.processNotLogin(request, response);
            return;//必须在此return，已在processNotLogin中重定向请求
        }

        HttpSession session = request.getSession(false);
        TestResult testResult = null;
        int trId = -1;
        Customer customer = null;
        if (session.getAttribute(Constants.SESSION_TESTRESULT) != null) {
            testResult = (TestResult) session.getAttribute(Constants.SESSION_TESTRESULT);
        }
        if (session.getAttribute(Constants.SESSION_TEST_RESERVATION) != null) {
            trId = (int) session.getAttribute(Constants.SESSION_TEST_RESERVATION);
        }

        TestResultView testResultView = new TestResultView();
        testResultView.setTestResult(testResult);
        testResultView.setTest(testResult.getTest());
        testResultView.display(response);
    }


}
