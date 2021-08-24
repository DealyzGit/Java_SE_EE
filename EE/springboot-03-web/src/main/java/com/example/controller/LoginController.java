package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @RequestMapping("/user/login")

    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model){
        if(StringUtils.hasLength(username) && "123456".equals(password)){
            return "dashboard";
        }else {
            model.addAttribute("msg","用户名或者密码错误！");
            return "index";
        }
    }
}
