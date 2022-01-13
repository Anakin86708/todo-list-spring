package com.ariel.springweb.controller;

import com.ariel.springweb.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class TodoController {

    @Autowired
    TodoService service;

    @RequestMapping(value = "/todo-list", method = RequestMethod.GET)
    public String showTodoPage(Model model) {
        String user = (String) model.getAttribute("user");
        model.addAttribute("todos", service.getTodosFromUser(user));
        model.addAttribute("user", user);
        return "todo-list";
    }
}
