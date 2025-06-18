package com.microservice.student.service;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.microservice.student.model.Student;
import com.microservice.student.repository.StudentRepository;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.*;
import java.util.Random;

@Profile("test")
@Component
public class StudentDataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 50; i++) {
            Student student = Student.builder()
                    .name(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .courseId((long) faker.number().numberBetween(1, 10))
                    .certificateId((long) faker.number().numberBetween(1, 100))
                    .enrollmentId((long) faker.number().numberBetween(1, 100))
                    .supportId((long) faker.number().numberBetween(1, 50))
                    .build();
            studentRepository.save(student);
        }
    }
}
