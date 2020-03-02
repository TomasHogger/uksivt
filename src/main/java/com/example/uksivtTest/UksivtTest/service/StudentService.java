package com.example.uksivtTest.UksivtTest.service;

import com.example.uksivtTest.UksivtTest.model.Student;
import com.example.uksivtTest.UksivtTest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student findByLogin(String login) {
        return studentRepository.findByLogin(login);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }
}
