package com.example.todolist.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor (force = true)
@Data
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    public Todo(String content, Boolean isCompleted, AppUser user) {
        this.content = content;
        this.isCompleted = isCompleted;
        this.user = user;
    }
}
