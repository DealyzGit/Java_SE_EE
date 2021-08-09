package org.fangsoft.testcenter.web.servlet;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.model.TestReservation;
import org.fangsoft.testcenter.web.URLConfig;
import org.fangsoft.view.TestCenterView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "DisplayTestCenterViewServlet", value = "/testCenterView")
public class DisplayTestCenterViewServlet extends TestCenterServlet implements Servlet {

    //    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.displayView(request, response);
//    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!this.isLogined(request, response)) {
            this.processNotLogin(request, response);
            return;//必须在此return，已在processNotLogin中重定向请求
        }
        this.displayView(request, response);
    }

    private void displayView(HttpServletRequest request, HttpServletResponse response) throws IOException {//显示web视图

        TestCenterView view = new TestCenterView();
        List<Test> test = this.getTestCenterFacade().findAllTest();
        view.setTestList(test);

        view.setUrlLogout(URLConfig.urlLogout);//登出url
        view.setUrlStartTest(URLConfig.urlStartTest);//开始考试url
        view.setUrlTestDetail(URLConfig.urlTestDetail);//浏览考试详细信息url
        view.setUrlTestResult(URLConfig.urlTestResult);//浏览考试结果url
        view.setUrlPayment(URLConfig.urlPayment);//支付考试url
        view.setLocale(request.getLocale());
        String userId = this.getCustomer(request).getUserId();
        view.setUserId(userId);

        try {
            response.setContentType("text/html;charset=UTF-8");
            String test_Id = request.getParameter("testId");
            if (test_Id != null) {
                int testId = Integer.parseInt(test_Id);
                Date date = new Date();

                Test testById = this.getTestCenterFacade().findTestByPK(testId);
                Customer customer = this.getCustomer(request);
                TestReservation testReservation = new TestReservation();

                testReservation.setStatus(TestReservation.Status.ORDERED);
                testReservation.setOrderDate(date);
                testReservation.setTest(testById);
                testReservation.setCustomer(customer);
                this.getTestCenterFacade().getDaoFactory().getTestReservationDao().save(testReservation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.setTestList(this.getTestCenterFacade().findAllTest());
        view.setTestResultList(this.getTestCenterFacade().findTestResultByUserId(userId));
        view.setTestReservationList(this.getTestCenterFacade().findActiveTestReservationByUserId(userId));

        view.display(response);
    }
}


