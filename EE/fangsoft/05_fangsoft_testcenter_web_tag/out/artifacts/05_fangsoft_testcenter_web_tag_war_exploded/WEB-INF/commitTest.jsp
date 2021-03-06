<%@ page import="org.fangsoft.testcenter.web.JSPUtil" %>
<%@ page import="org.fangsoft.testcenter.web.Constants" %>
<%@ page import="org.fangsoft.testcenter.config.Configuration" %>
<%@ page import="java.util.*" %>
<%@ page import="org.fangsoft.testcenter.web.URLConfig" %>
<%@ page import="org.fangsoft.testcenter.web.bean.TestResultListBean" %>
<%@ page import="org.fangsoft.testcenter.model.*" %>
<%@ page import="org.fangsoft.testcenter.web.bean.TestReservationListBean" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    TestResult testResult = null;
    int trId = -1;
    session= request.getSession(false);

    if (session.getAttribute(Constants.SESSION_TESTRESULT) != null) {
        testResult = (TestResult) session.getAttribute(Constants.SESSION_TESTRESULT);
    }

    if (session.getAttribute("session_test_reservation") != null) {
        trId = (int) session.getAttribute("session_test_reservation");
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

    request.getRequestDispatcher("/" + URLConfig.urlTestResult.replace("{testResultId}",String.valueOf(testResult.getTest().getId()))).forward(request, response);

%>
