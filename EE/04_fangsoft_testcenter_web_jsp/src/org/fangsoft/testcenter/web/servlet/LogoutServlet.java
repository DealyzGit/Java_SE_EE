package org.fangsoft.testcenter.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.URLConfig;
import org.fangsoft.util.DataValidator;
import org.fangsoft.view.LoginView;

import java.io.IOException;

@WebServlet(name = "LogoutServlet ", value = "/logout")
public class LogoutServlet extends TestCenterServlet {
    private void displayView(HttpServletResponse response, String userId, String msg) throws IOException {
        LoginView view = new LoginView();
        view.setLoginAction(URLConfig.urlLoginAction);
        view.setErrorMsg(msg);
        view.setUserId(userId);
        view.display(response);
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute(Constants.SESSION_TESTRESULT) != null) {
                TestResult testResult = (TestResult) session.getAttribute(Constants.SESSION_TESTRESULT);
                int testReservationId = (Integer) session.getAttribute(Constants.SESSION_TEST_RESERVATION);
                this.getTestCenterFacade().commitTest(testResult, testReservationId);
            }
            session.invalidate();
        }
        response.sendRedirect(URLConfig.urlLoginView);


        String userId = DataValidator.validate(request.getParameter("userId"));
        String password = DataValidator.validate(request.getParameter("password"));
        Customer customer = this.getTestCenterFacade().login(userId, password);
        if (customer != null) {
            session.setAttribute(Constants.SESSION_USERID, customer);
            request.getRequestDispatcher("/" + URLConfig.urlTestCenterView).forward(request, response);
            return;
        } else {
            this.displayView(response, "", "invalid userId or password");
        }
    }

}
