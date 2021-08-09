package org.fangsoft.testcenter.web.action;

import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.model.Question;
import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.model.TestReservation;
import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.JSPUtil;
import org.fangsoft.testcenter.web.URLConfig;
import org.fangsoft.testcenter.web.framework.ActionConfig;
import org.fangsoft.testcenter.web.framework.ResponsePage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class CommitTestAction extends TestCenterAction{
    @Override
    public ResponsePage doProcess(HttpServletRequest request, HttpServletResponse response, ActionConfig actionConfig) {

        TestResult testResult = null;

        HttpSession session = request.getSession(false);

        if (session.getAttribute(Constants.SESSION_TESTRESULT) != null) {
            testResult = (TestResult) session.getAttribute(Constants.SESSION_TESTRESULT);
        }

        int trId = (int) session.getAttribute("session_reservation");


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

        Set<String> strings = parameters.keySet();
        Iterator it1 = strings.iterator();
        List<String> oldAnswer = new ArrayList<>(testResult.getTest().getNumQuestion());

        String[] old_answer = {"", "", ""};
        while (it1.hasNext()) {
            String ts = (String) it1.next();
            if (ts.split("_").length != 1) {
                int oder = Integer.parseInt(ts.split("_")[0]);
                for (QuestionResult qr : testResult.getQuestionResult()) {
                    if (qr.getQuestion().getId() == oder) {
                        old_answer[oder] += ts.split("_")[1];
                        qr.setAnswer(old_answer[oder]);
                    }
                }

            }
        }
        JSPUtil.getTestCenterFacade().commitTest(testResult, trId);
        request.setAttribute("testResultId",testResult.getId());
        return actionConfig.getResponsePage(SUCCESS);
    }
}
