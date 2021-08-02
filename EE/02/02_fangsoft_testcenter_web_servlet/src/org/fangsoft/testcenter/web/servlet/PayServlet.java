package org.fangsoft.testcenter.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fangsoft.testcenter.model.TestReservation;
import org.fangsoft.view.PaymentReportView;


import java.io.IOException;

@WebServlet(name = "PayServlet ", value = "/pay")
public class PayServlet extends TestCenterServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.displayView(request, response);
    }

    private void displayView(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //显示web视图
        PaymentReportView paymentReportView=new PaymentReportView();
        response.setContentType("text/html;charset=UTF-8");
        try {
            int testReservationId = Integer.parseInt(request.getParameter("testReservationId"));
            this.getTestCenterFacade().getDaoFactory().getTestReservationDao().updateStatus(testReservationId, TestReservation.Status.PAYED);
        }catch (Exception e){
            e.printStackTrace();
        }
        paymentReportView.display(response);
    }
}
