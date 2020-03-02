package com.example.uksivtTest.UksivtTest.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private boolean isAlreadyCompleted;

}
