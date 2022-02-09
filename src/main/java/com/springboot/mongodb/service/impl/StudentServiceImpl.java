package com.springboot.mongodb.service.impl;

import com.springboot.mongodb.exception.ResourceNotFoundException;
import com.springboot.mongodb.model.Student;
import com.springboot.mongodb.repository.StudentRepository;
import com.springboot.mongodb.service.SequenceGeneratorService;
import com.springboot.mongodb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        student.setId(sequenceGeneratorService.generateSequenceNumber(Student.SEQUENCE_NAME));
     studentRepository.save(student);
    }

    @Override
    public ResponseEntity<Student> getStudent(Integer id) {
        Student student=studentRepository.findById(id).orElseThrow(ResourceNotFoundException.builder().message("Student not found for this id:"+id)::build);
        return ResponseEntity.ok().body(student);
    }

    @Override
    public Map<String, Boolean> delete(Integer id){
        Student student=studentRepository.findById(id).orElseThrow(ResourceNotFoundException.builder().message("Student not found for this id:"+id)::build);
        studentRepository.delete(student);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

    @Override
    public ResponseEntity<Student> updateStudent(Integer id, Student student) {
        Student oldStudent=studentRepository.findById(id).orElseThrow(ResourceNotFoundException.builder().message("Student not found for this id:"+id)::build);
        oldStudent.update(student);
        final Student updatedStudent=studentRepository.save(oldStudent);
         return ResponseEntity.ok().body(updatedStudent);
    }
}
