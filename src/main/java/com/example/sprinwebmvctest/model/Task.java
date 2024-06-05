package com.example.sprinwebmvctest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "grade")
    private int grade;

    public Task(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}
