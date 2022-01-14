package com.ariel.springweb.controller;

import com.ariel.springweb.model.Todo;
import com.ariel.springweb.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("user")
public class TodoController {

    @Autowired
    TodoService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
    }

    @RequestMapping(value = "/todo-list", method = RequestMethod.GET)
    public String showTodoPage(Model model) {
        model.addAttribute("todos", service.getTodosFromUser(getUser(model)));
        return "todo-list";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddNewTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "add-todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addNewTodo(Model model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "add-todo";
        }
        service.addTodo(getUser(model), todo.getDescription(), todo.getTargetDate(), todo.isDone());
        return "redirect:/todo-list";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String removeTodoById(@RequestParam("id") int id, Model model) {
        service.removeTodo(id);
        return "redirect:/todo-list";
    }

    private String getUser(Model model) {
        return (String) model.getAttribute("user");
    }
}
