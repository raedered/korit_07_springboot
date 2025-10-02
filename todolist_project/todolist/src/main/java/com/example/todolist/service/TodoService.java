package com.example.todolist.service;

import com.example.todolist.domain.AppUser;
import com.example.todolist.domain.AppUserRepository;
import com.example.todolist.domain.Todo;
import com.example.todolist.domain.TodoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final AppUserRepository appUserRepository;

    public TodoService(TodoRepository todoRepository, AppUserRepository appUserRepository) {
        this.todoRepository = todoRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo (Todo todo) {
        return todoRepository.save(todo);
    }

    public Optional<Todo> getTodoById (Long id) {
        return todoRepository.findById(id);
    }

    public boolean deleteTodo(Long id) {
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Todo> updateTodo(Long id, Todo todoDetails) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setContent(todoDetails.getContent());
                    todo.setUser(todoDetails.getUser());
                    return todo;
                });
    }

    @Transactional
    public Optional<Todo> updateTodoStatus(Long id) {
        return todoRepository.findById(id)
                .map(todo -> {
                    Boolean current = todo.getIsCompleted();
                    todo.setIsCompleted(current == null ? true : !current);
                    return todo;
                });
    }

    @Transactional
    public void clearCompletedTodos() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        AppUser user = appUserRepository.findByUsername(username)
                        .orElse(null);
        if(user != null) {
            todoRepository.deleteByUserAndIsCompletedTrue(user);
        }

        todoRepository.deleteByUserAndIsCompletedTrue(user);
    }
}
