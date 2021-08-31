package org.fangsoft.testcenter.web.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.web.Constants;
import org.fangsoft.testcenter.web.URLConfig;
import org.fangsoft.util.DataValidator;
import org.fangsoft.view.LoginView;
import org.fangsoft.web.util.CookieUtil;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends TestCenterServlet {

    private void displayView(HttpServletResponse response,String userId,String msg) throws IOException {
        LoginView view=new LoginView();
        view.setLoginAction(URLConfig.urlLoginAction);
        view.setErrorMsg(msg);
        view.setUserId(userId);
        view.display(response);
    }


    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId=DataValidator.validate(request.getParameter("userId"));
        String password=DataValidator.validate(request.getParameter("password"));

        if(request.getParameter("save")!=null){//选中已保存
            this.saveCookie(response, userId);
        }else{//未选中保存
            CookieUtil.killCookie(request, response,Constants.COOKIE_USERID);
        }
        Customer customer=this.getTestCenterFacade().login(userId,password);

        if(customer!=null){//登录成功
            HttpSession session=request.getSession();
            session.setAttribute(Constants.SESSION_USERID, customer);
            request.getRequestDispatcher("/"+URLConfig.urlTestCenterView).forward(request, response);
            return;
        }else{//登录不成功
            String cookieUserId= CookieUtil.getCookieValue(request,Constants.COOKIE_USERID);
            this.displayView(response,cookieUserId,"invalid userId or password");
        }
    }

    private void saveCookie(HttpServletResponse response,final String cookieValue){
        Cookie cookie=new Cookie(Constants.COOKIE_USERID,cookieValue);
        cookie.setMaxAge(31536);//Cookie保存期限为一年
        response.addCookie(cookie);
    }


}
