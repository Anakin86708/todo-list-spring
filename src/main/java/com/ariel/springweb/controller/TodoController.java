package com.ariel.springweb.controller;

import com.ariel.springweb.model.Todo;
import com.ariel.springweb.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoController {

    @Autowired
    private TodoService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
    }

    @RequestMapping(value = "/todo-list", method = RequestMethod.GET)
    public String showTodoPage(Model model) {
        String user = getUser();
        model.addAttribute("user", user);
        model.addAttribute("todos", service.getTodosFromUser(user));
        return "todo-list";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddNewTodo(Model model) {
        String user = getUser();
        model.addAttribute("user", user);
        model.addAttribute("todo", new Todo(user));
        return "add-todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showEditTodo(Model model, @RequestParam int id) {
        model.addAttribute("user", getUser());
        model.addAttribute("todo", service.retrieveTodo(id));
        return "add-todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addNewTodo(Model model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "add-todo";
        }
        service.addTodo(todo);
        return "redirect:/todo-list";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodoById(Model model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "add-todo";
        }

        service.updateTodo(todo);
        return "redirect:/todo-list";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String removeTodoById(Model model, @RequestParam("id") int id) {
        service.removeTodo(id);
        return "redirect:/todo-list";
    }

    private String getUser() {
        return GeneralController.getLoggedUser();
    }
}
