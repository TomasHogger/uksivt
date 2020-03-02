package com.example.uksivtTest.UksivtTest.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "questions")
@Entity
@Data
public class QuestionsDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "student_id")
    private int studentId;
    @Column(length=100000)
    private String json;

}
