package org.fangsoft.testcenter.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.view.TestDetailView;

import java.io.IOException;

@WebServlet(name = "DisplayTestDetailViewServlet", value = "/testDetailView")
public class DisplayTestDetailViewServlet extends TestCenterServlet {



    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!this.isLogined(request, response)) {
            this.processNotLogin(request, response);
            return;//必须在此return，已在processNotLogin中重定向请求
        }
        this.displayView(request, response);
    }

    private void displayView(HttpServletRequest request, HttpServletResponse response) throws IOException {//显示web视图

        response.setContentType("text/html;charset=UTF-8");
        String testId = request.getParameter("testId");
        Test test=this.getTestCenterFacade().findTestByPK(Integer.parseInt(testId)+1);

        TestDetailView testDetailView = new TestDetailView();
        testDetailView.setTest(test);
        testDetailView.display(response);
    }

}
