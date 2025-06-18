package com.microservice.course.service;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.microservice.course.model.Course;
import com.microservice.course.repository.ICourseRepository;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.*;


@Profile("test")
@Component
public class CourseDataLoader implements CommandLineRunner {

    @Autowired
    private ICourseRepository courseRepository;

    private final Faker faker = new Faker();

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 20; i++) {
            Course course = Course.builder()
                    .name(faker.educator().course())
                    .teacher(faker.name().fullName())
                    .build();
            courseRepository.save(course);
        }
    }
}
