package com.ariel.springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("error")
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ModelAndView modelView = new ModelAndView();
        modelView.addObject("exception", ex);
        modelView.addObject("url", request.getRequestURL());
        modelView.setViewName("error");
        return modelView;
    }
}
