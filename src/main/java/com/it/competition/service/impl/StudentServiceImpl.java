package com.it.competition.service.impl;

import com.it.competition.dao.StudentMapper;
import com.it.competition.domain.Student;
import com.it.competition.domain.StudentExample;
import com.it.competition.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student login(Student student) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andStudentNumEqualTo(student.getStudentNum()).andPasswordEqualTo(student.getPassword());
        List<Student> students = studentMapper.selectByExample(example);
        return students != null && students.size() > 0 ? students.get(0) : null;
    }

    @Override
    public Student register(Student student) {
        return studentMapper.insert(student) > 0 ? student : null;
    }

    @Override
    public Student getStudentByStudentNum(String studnetNum) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andStudentNumEqualTo(studnetNum);
        List<Student> students = studentMapper.selectByExample(example);
        return students != null && students.size() > 0 ? students.get(0) : null;
    }

    @Override
    public List<Student> getStudents() {
        return studentMapper.selectByExample(new StudentExample());
    }
}
