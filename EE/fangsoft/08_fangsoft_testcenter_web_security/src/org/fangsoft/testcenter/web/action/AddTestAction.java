package org.fangsoft.testcenter.web.action;

import org.fangsoft.testcenter.web.framework.ActionConfig;
import org.fangsoft.testcenter.web.framework.ResponsePage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTestAction extends TestCenterAction {

    public ResponsePage doProcess(HttpServletRequest request, HttpServletResponse response, ActionConfig actionConfig) {


        return actionConfig.getResponsePage(SUCCESS);
    }
}
