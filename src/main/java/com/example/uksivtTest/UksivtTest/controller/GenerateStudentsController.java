package com.example.uksivtTest.UksivtTest.controller;

import com.example.uksivtTest.UksivtTest.model.Student;
import com.example.uksivtTest.UksivtTest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class GenerateStudentsController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/generate")
    public String generate(@RequestParam String pref, @RequestParam Integer count) {
        StringBuilder stringBuilder = new StringBuilder();

        List<Student> alreadyExists = studentRepository.findByLoginLike(pref);
        int n = 0;
        if (alreadyExists != null && !alreadyExists.isEmpty()) {
            alreadyExists.sort((o1, o2) -> o1.getLogin().compareTo(o2.getPassword()));
            n = Integer.parseInt(alreadyExists.get(alreadyExists.size() - 1).getLogin().split("_")[1]);
        }

        List<Student> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Student student = new Student();
            student.setLogin(pref + "_" + (i + n));
            student.setPassword(UUID.randomUUID().toString().substring(0, 8));
            student.setAlreadyCompleted(false);
            list.add(student);

            stringBuilder
                    .append("login: ")
                    .append(student.getLogin())
                    .append(" Password: ")
                    .append(student.getPassword())
                    .append("<br>");
        }

        studentRepository.saveAll(list);

        return stringBuilder.toString();
    }

}
