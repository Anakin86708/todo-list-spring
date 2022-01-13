package com.ariel.springweb.service;

import com.ariel.springweb.model.Todo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {
    private final List<Todo> todos = new ArrayList<>();
    private int idCounter = 0;

    public TodoService() {
        todos.add(new Todo(0, "ariel", "test", new Date(Instant.now().toEpochMilli()), false));
    }

    public List<Todo> getTodosFromUser(String user) {
        return todos.stream().filter(todo -> todo.getUser().equals(user)).toList();
    }

    public void addTodo(String user, String description, Date targetDate, boolean isDone) {
        int id = idCounter++;
        todos.add(new Todo(id, user, description, targetDate, isDone));
    }

    public void removeTodo(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }
}
