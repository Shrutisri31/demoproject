package com.tutorial.todoapp.service;

import com.tutorial.todoapp.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {
    private final List<Todo> todos = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo createTodo(String title) {
        Todo todo = new Todo(idCounter.incrementAndGet(), title, false);
        todos.add(todo);
        return todo;
    }

    public Optional<Todo> getTodoById(Long id) {
        return todos.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public Optional<Todo> updateTodo(Long id, String title, boolean completed) {
        Optional<Todo> optionalTodo = getTodoById(id);
        optionalTodo.ifPresent(todo -> {
            todo.setTitle(title);
            todo.setCompleted(completed);
        });
        return optionalTodo;
    }
    public boolean deleteTodo(Long id) {
        return todos.removeIf(todo -> todo.getId().equals(id));
    }
}