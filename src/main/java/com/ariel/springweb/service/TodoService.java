package com.ariel.springweb.service;

import com.ariel.springweb.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<Todo> getTodosFromUser(String user) {
        return repository.findByUser(user);
    }

    public void addTodo(Todo todo) {
        repository.save(todo);
    }

    public Todo retrieveTodo(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Unable to find ToDo"));
    }

    public void removeTodo(long id) {
        repository.deleteById(id);
    }

    public void updateTodo(Todo todo) {
        removeTodo(todo.getId());
        repository.save(todo);
    }
}
