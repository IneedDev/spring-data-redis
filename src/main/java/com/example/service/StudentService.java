package com.example.service;

import com.example.model.Student;
import com.example.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService{

    @Autowired
    StudentRepository studentRepository;

    public void sava() {
        Student student = new Student("Eng2015001dsdsdsdsd", "John Doe", Student.Gender.MALE, 1);
        studentRepository.save(student);
    }
}
