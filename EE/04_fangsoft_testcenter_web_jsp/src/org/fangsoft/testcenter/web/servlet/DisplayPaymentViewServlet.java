package org.fangsoft.testcenter.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fangsoft.testcenter.model.TestReservation;
import org.fangsoft.view.PaymentView;

import java.io.IOException;

@WebServlet(name = "DisplayPaymentViewServlet", value = "/paymentView")
public class DisplayPaymentViewServlet extends TestCenterServlet {

    private void displayView(HttpServletRequest request, HttpServletResponse response) throws IOException {//显示web视图
        PaymentView view = new PaymentView();
        try {
            response.setContentType("text/html;charset=UTF-8");
            int testReservationId = Integer.parseInt(request.getParameter("testReservationId"));
            view.setTestReservationId(testReservationId);
            String userId=this.getTestCenterFacade().getDaoFactory().getTestReservationDao().findTestReservationByPK(testReservationId).getCustomer().getUserId();
            view.setUserID(userId);
            view.setMoney(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.display(response);
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.displayView(request, response);
    }
}
