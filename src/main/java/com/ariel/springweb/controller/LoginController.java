package com.ariel.springweb.controller;

import com.ariel.springweb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginMessage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginMessage(@RequestParam String user, @RequestParam String email, @RequestParam String password, @RequestParam(name = "validationMessage", defaultValue = "") String validationMessage, Model model) {
        if (loginService.validateUserData(email, password)) {
            model.addAttribute("user", user);
            return "welcome";
        }
        model.addAttribute("validationMessage", "Invalid combination");
        return "login";
    }

}
