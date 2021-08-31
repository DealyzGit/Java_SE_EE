package org.fangsoft.testcenter.web.action;

import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.TestReservation;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.JSPUtil;
import org.fangsoft.testcenter.web.framework.ActionConfig;
import org.fangsoft.testcenter.web.framework.ResponsePage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ReserveTestAction extends TestCenterAction {

    public ResponsePage doProcess(HttpServletRequest request, HttpServletResponse response, ActionConfig actionConfig) {

        int testId = Integer.parseInt(request.getParameter("testId"));

        HttpSession session = request.getSession(false);
        Customer customer = (Customer) session.getAttribute(Constants.SESSION_USERID);

        TestReservation testReservation = JSPUtil.getTestCenterFacade().reserveTest(testId, customer);

        return actionConfig.getResponsePage(SUCCESS);
    }
}
