package com.springboot.mongodb.controller;

import com.springboot.mongodb.model.Student;
import com.springboot.mongodb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping(value = "/students",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("id") Integer id){
        return studentService.getStudent(id);
    }
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add( @RequestBody Student student){
        studentService.addStudent(student);
    }
    @DeleteMapping(value = "/{id}")
    public Map<String,Boolean> delete(@PathVariable("id") Integer id){
        return studentService.delete(id);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer id,@RequestBody Student student){
  return studentService.updateStudent(id,student);
    }

}
