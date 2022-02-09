package com.springboot.mongodb.service;

import com.springboot.mongodb.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Student> getAllStudents();
    void addStudent(Student student);
    ResponseEntity<Student> getStudent(Integer id);
    Map<String,Boolean> delete(Integer id);
    ResponseEntity<Student> updateStudent(Integer id,Student student);
}
