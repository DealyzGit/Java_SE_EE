package org.fangsoft.testcenter.web.action;

import org.fangsoft.testcenter.model.TestReservation;
import org.fangsoft.testcenter.web.JSPUtil;
import org.fangsoft.testcenter.web.framework.ActionConfig;
import org.fangsoft.testcenter.web.framework.ResponsePage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayAction extends TestCenterAction {
    @Override
    public ResponsePage doProcess(HttpServletRequest request, HttpServletResponse response, ActionConfig actionConfig) {

        int trId = Integer.parseInt(request.getParameter("testReservationId"));
        JSPUtil.getTestCenterFacade().updateTestReservationStatus(trId, TestReservation.Status.PAYED);

        return actionConfig.getResponsePage(SUCCESS);
    }
}


