package com.ariel.springweb.controller;

import com.ariel.springweb.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@SessionAttributes("user")
public class TodoController {

    @Autowired
    TodoService service;

    @RequestMapping(value = "/todo-list", method = RequestMethod.GET)
    public String showTodoPage(Model model) {
        String user = (String) model.getAttribute("user");
        refreshModelTodos(model, user);
        return "todo-list";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddNewTodo(Model model) {
        return "add-todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addNewTodo(@RequestParam String desc, @RequestParam String targetDate, @RequestParam(value = "isDone", defaultValue = "false") boolean isDone, Model model) throws ParseException {
        String user = (String) model.getAttribute("user");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        service.addTodo(user, desc, dateFormat.parse(targetDate), isDone);
        return "redirect:/todo-list";
    }

    private void refreshModelTodos(Model model, String user) {
        model.addAttribute("todos", service.getTodosFromUser(user));
    }
}
