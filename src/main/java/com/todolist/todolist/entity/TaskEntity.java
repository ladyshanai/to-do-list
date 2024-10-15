package com.todolist.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="task")
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
}
