package com.it.competition.service;

import com.it.competition.domain.Student;

import java.util.List;

public interface StudentService {

    Student login(Student student);

    Student register(Student student);

    Student getStudentByStudentNum(String studnetNum);

    List<Student> getStudents();
}
