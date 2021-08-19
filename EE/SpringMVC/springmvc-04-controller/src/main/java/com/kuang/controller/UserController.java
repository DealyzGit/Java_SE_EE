package com.kuang.controller;

import com.kuang.pojo.User;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/t1")
    public String test1(@RequestParam("username") String name, Model model){

        System.out.println(name);
        model.addAttribute("msg",name);

        return "test";
    }
    @GetMapping("/t2")
    public String test2(User user, Model model){

        System.out.println(user);
        model.addAttribute("msg",user);

        return "test";
    }


    @GetMapping("/t3")
    public String test3(ModelMap map){

        return "test";
    }


}
