package org.fangsoft.testcenter.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.Question;
import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.URLConfig;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "CommitTestServlet", value = "/commitTest")
public class CommitTestServlet extends TestCenterServlet {
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
        if (session.getAttribute(Constants.SESSION_USERID) != null) {
            customer = (Customer) session.getAttribute(Constants.SESSION_USERID);
        }
        if (session.getAttribute(Constants.SESSION_TEST_RESERVATION) != null) {
            trId = (int) session.getAttribute(Constants.SESSION_TEST_RESERVATION);
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        HashMap<String, String> parameters = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            parameters.put(parameterName, request.getParameter(parameterName));
        }

        for (Question q : testResult.getTest().getQuestion()) {
            if (q != null) {
                q.assignLabel(Configuration.CHOICE_LABEL);
            }
        }

        int testId = testResult.getTest().getId();
        Set<String> strings = parameters.keySet();
        Iterator it1 = strings.iterator();
        List<String> oldAnswer = new ArrayList<>(testResult.getTest().getNumQuestion());

        String[] old_answer = {"","",""};
        while (it1.hasNext()) {
            String ts = (String) it1.next();
            if (ts.split("_").length != 1) {
                int oder = Integer.parseInt(ts.split("_")[0]);
                for (QuestionResult qr : testResult.getQuestionResult()) {
                    if (qr.getQuestion().getId() == oder) {
                        old_answer[oder]+=ts.split("_")[1];
                        qr.setAnswer(old_answer[oder]);
                    }
                }

            }
        }
        this.getTestCenterFacade().commitTest(testResult, trId);

        request.getRequestDispatcher("/" + URLConfig.urlTestResult).forward(request, response);
        return;

    }


}
