package com.example.uksivtTest.UksivtTest.controller;

import com.example.uksivtTest.UksivtTest.model.Student;
import com.example.uksivtTest.UksivtTest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String index() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findByLogin(user.getUsername());
        if (student.isAlreadyCompleted()) {
            return "redirect:./login";
        }
        return "index";
    }
}
