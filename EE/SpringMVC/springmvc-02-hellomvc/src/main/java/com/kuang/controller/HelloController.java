package com.kuang.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv=new ModelAndView();
        String res="HelloSpringMvc";
        mv.addObject("msg",res);
        mv.setViewName("hello");
        return mv;
    }
}
