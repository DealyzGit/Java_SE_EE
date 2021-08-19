package com.kuang.controller;

import com.kuang.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {
    @RequestMapping("/t1")
    public String test() {
        return "hello";
    }

    @lombok.SneakyThrows
    @RequestMapping("/a1")
    public void a1(String name, HttpServletResponse response) {
        System.out.println(name);
        if ("tong".equals(name)) {
            response.getWriter().println("true");
        } else {
            response.getWriter().println("false");
        }
    }

    @RequestMapping("/a2")
    public List<User> a2(){
        List<User> userList =new ArrayList<User>();
        userList.add(new User("tong",24,"man"));
        userList.add(new User("tong",25,"man"));
        userList.add(new User("tong",26,"man"));
        return userList;
    }

    @RequestMapping("/a3")
    public String a3(String name,String pwd){
        System.out.println(name+" "+pwd);
        String msg="";
        if (name !=null){
            if("tong".equals(name)){
                msg="ok";
            }
            else {
                msg="error!";
            }
        }

        if (pwd !=null){
            if("123".equals(pwd)){
                msg="ok";
            }
            else {
                msg="error!";
            }
        }
        return msg;
    }
}
