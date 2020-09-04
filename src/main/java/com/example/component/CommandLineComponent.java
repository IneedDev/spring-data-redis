package com.example.component;

import com.example.model.Event;
import com.example.model.Student;
import com.example.repo.EventRepository;
import com.example.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineComponent implements CommandLineRunner  {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public void run(String... args) throws Exception {
        Student student = new Student("Te666st", "John Doe", Student.Gender.MALE, 1);
//        Event event = new Event("12", "123456789", true, "KRK-001", 1);
//        studentRepository.save(student);
//        eventRepository.save(event);
//
//        System.out.println(eventRepository.findAll());;

        System.out.println("test");
    }
}
