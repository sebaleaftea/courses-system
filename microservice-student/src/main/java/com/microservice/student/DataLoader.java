package com.microservice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.microservice.student.model.Student;
import com.microservice.student.repository.StudentRepository;

import net.datafaker.Faker;

@Component
@Profile("test")
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) {
        if (studentRepository.count() == 0) {
            Faker faker = new Faker();
            for (int i = 0; i < 5; i++) {
                studentRepository.save(Student.builder()
                    .name(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .courseId((long) faker.number().numberBetween(1, 5))
                    .certificateId((long) faker.number().numberBetween(1, 5))
                    .enrollmentId((long) faker.number().numberBetween(1, 5))
                    .supportId((long) faker.number().numberBetween(1, 5))
                    .build());
            }
        }
    }
}
