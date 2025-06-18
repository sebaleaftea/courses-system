package com.microservice_certificate.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.microservice_certificate.model.Certificate;
import com.microservice_certificate.repository.ICertificateRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

@Profile("test")
@Component
public class CertificateDataLoader implements CommandLineRunner {

    @Autowired
    private ICertificateRepository certificateRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 30; i++) {
            LocalDate issue = LocalDate.now().minusDays(random.nextInt(365));
            LocalDate expiration = issue.plusMonths(1 + random.nextInt(3)); // 1 a 3 meses después

            Certificate certificate = Certificate.builder()
                    .name(faker.educator().course() + " Certificate")
                    .issueDate(Date.valueOf(issue))
                    .expirationDate(Date.valueOf(expiration))
                    .build();

            certificateRepository.save(certificate);
        }
    }
}
