package org.fangsoft.testcenter.web.action;

import org.fangsoft.testcenter.web.framework.Action;
import org.fangsoft.testcenter.web.framework.ActionConfig;
import org.fangsoft.testcenter.web.framework.ResponsePage;

import java.util.HashMap;
import java.util.Map;

public class ActionConfigMapping {
    private static final Map<String, ActionConfig> request2ActionMap =
            new HashMap<String, ActionConfig>();

    public static Map<String, ActionConfig> getRequest2ActionMap() {
        return request2ActionMap;
    }

    public static ActionConfig addActionConfig(String requestURI, String actionClazz) {
        ActionConfig actionConfig = new ActionConfig();
        try {
            actionConfig.setAction((Action) Class.forName(actionClazz).
                    newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request2ActionMap.put(requestURI, actionConfig);
        return actionConfig;
    }

    static {
//login.do
        ActionConfig loginActionConfig = addActionConfig("login.do",
                "org.fangsoft.testcenter.web.action.LoginAction");
        loginActionConfig.addResponsePage("failure", "loginView.jsp");
        loginActionConfig.addResponsePage("success", "testCenterView");
//commitTest.do
        ActionConfig commitTestActionConfig = addActionConfig("commitTest.do",
                "org.fangsoft.testcenter.web.action.CommitTestAction");
        commitTestActionConfig.addResponsePage("failure",
                "WEB-INF/paymentReportView.jsp");
        commitTestActionConfig.addResponsePage("success",
                "WEB-INF/testResultView.jsp");
        commitTestActionConfig.addResponsePage("notLogined", "loginView.jsp",
                ResponsePage.SendMode.REDIRECT);
//logout.do
        ActionConfig logoutActionConfig = addActionConfig("logout.do",
                "org.fangsoft.testcenter.web.action.LogoutAction");
        logoutActionConfig.addResponsePage("success", "loginView.jsp",
                ResponsePage.SendMode.REDIRECT);
//pay.do
        ActionConfig payActionConfig = addActionConfig("pay.do",
                "org.fangsoft.testcenter.web.action.PayAction");
        payActionConfig.addResponsePage("success",
                "WEB-INF/paymentReportView.jsp");
        payActionConfig.addResponsePage("notLogined", "loginView.jsp",
                ResponsePage.SendMode.REDIRECT);
// reserveTest.do
        ActionConfig reserveTestActionConfig = addActionConfig("reserveTest.do",
                "org.fangsoft.testcenter.web.action.ReserveTestAction");
        reserveTestActionConfig.addResponsePage("success",
                "WEB-INF/paymentView.jsp");
        reserveTestActionConfig.addResponsePage("notLogined", "loginView.jsp",
                ResponsePage.SendMode.REDIRECT);
// startTest.do
        ActionConfig startTestActionConfig = addActionConfig("startTest.do",
                "org.fangsoft.testcenter.web.action.StartTestAction");
        startTestActionConfig.addResponsePage("success",
                "WEB-INF/startTestView.jsp");
        startTestActionConfig.addResponsePage("notLogined", "loginView.jsp",
                ResponsePage.SendMode.REDIRECT);
    }


}
