package com.microservice.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;

import com.microservice.course.repository.ICourseRepository;
import com.microservice.course.model.Course;

@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        
        Faker faker = new Faker();
        
        for (int i = 0; i < 20; i++) {
            Course course = Course.builder()
                    .name(faker.educator().course())
                    .teacher(faker.name().fullName())
                    .build();
            courseRepository.save(course);
        }
    }
}
