package org.fangsoft.testcenter.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fangsoft.view.WelcomeView;

import java.io.IOException;

@WebServlet(name = "DisplayWelcomeViewServlet ", urlPatterns ={"/welcomeView "} )
public class DisplayWelcomeViewServlet extends TestCenterServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        doProcess(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WelcomeView welcomeView = new WelcomeView();
        welcomeView.display(response);

    }


}
