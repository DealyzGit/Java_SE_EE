package org.fangsoft.testcenter.web.action;

import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.framework.ActionConfig;
import org.fangsoft.testcenter.web.framework.ResponsePage;
import org.fangsoft.util.DataValidator;
import org.fangsoft.web.util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAction extends TestCenterAction {

    public ResponsePage doProcess(HttpServletRequest request, HttpServletResponse response, ActionConfig actionConfig)
            throws ServletException, IOException {
        String userId = DataValidator.validate(request.getParameter("userId"));
        String password = DataValidator.validate(request.getParameter("password"));
        if (request.getParameter("save") != null) {
            this.saveCookie(response, userId);
        } else {
            CookieUtil.killCookie(request, response, Constants.COOKIE_USERID);
        }
        Customer customer = this.getTestCenterFacade().login(userId, password);
        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_USERID, customer);
            return actionConfig.getResponsePage(SUCCESS);
        } else {
            request.setAttribute(Constants.REQUEST_LOGIN_ERROR_MSG,"invalid userId or password");
            return actionConfig.getResponsePage(FAILURE);
        }
    }

    private void saveCookie(HttpServletResponse response, String cookieValue) {
        Cookie cookie = new Cookie(Constants.COOKIE_USERID, cookieValue);
        cookie.setMaxAge(31536000);//1 year
        response.addCookie(cookie);
    }

}
