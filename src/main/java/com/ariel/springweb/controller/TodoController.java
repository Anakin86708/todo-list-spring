package com.ariel.springweb.controller;

import com.ariel.springweb.model.Todo;
import com.ariel.springweb.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
        String user = (String) model.getAttribute("user");
        refreshModelTodos(model, user);
        return "todo-list";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddNewTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "add-todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addNewTodo(Model model, Todo todo) throws ParseException {
        String user = (String) model.getAttribute("user");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        service.addTodo(user, todo.getDescription(), todo.getTargetDate(), todo.isDone());
        return "redirect:/todo-list";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String removeTodoById(@RequestParam("id") int id, Model model) {
        service.removeTodo(id);
        return "redirect:/todo-list";
    }

    private void refreshModelTodos(Model model, String user) {
        model.addAttribute("todos", service.getTodosFromUser(user));
    }
}
