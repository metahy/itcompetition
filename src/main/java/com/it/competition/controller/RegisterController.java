package com.it.competition.controller;

import com.it.competition.domain.Student;
import com.it.competition.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private StudentService studentService;

    @GetMapping("student")
    public String student() {
        return "register/student";
    }

    @PostMapping("student")
    public String student(Student student) {
        System.out.println(student);
        studentService.register(student);
        return "redirect:/login/student";
    }
}
