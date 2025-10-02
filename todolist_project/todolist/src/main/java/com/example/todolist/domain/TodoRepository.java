package com.example.todolist.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    void deleteByUserAndIsCompletedTrue(AppUser user);
}
