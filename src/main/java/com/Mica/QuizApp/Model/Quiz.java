package com.Mica.QuizApp.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
    private String title;
    @ManyToMany
    private List<Question> questions;
}
