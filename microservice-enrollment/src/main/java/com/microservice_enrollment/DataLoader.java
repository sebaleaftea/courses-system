package com.microservice_enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;

import com.microservice_enrollment.model.Enrollment;
import com.microservice_enrollment.repository.IEnrollmentRepository;

@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private IEnrollmentRepository enrollmentRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {

            Enrollment enrollment = Enrollment.builder()
                    .pay(faker.options().option("$10000", "$20000","25000", "NULL"))
                    .expiration(faker.options().option("1 mes", "2 meses", "3 meses"))
                    .status(faker.options().option("ENROLLED", "COMPLETED", "CANCELED"))
                    .build();

            enrollmentRepository.save(enrollment);
        }
    }
}