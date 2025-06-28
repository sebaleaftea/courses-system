package com.microservice_certificate;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;

import com.microservice_certificate.model.Certificate;
import com.microservice_certificate.repository.ICertificateRepository;

@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ICertificateRepository certificateRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for (int i = 0; i < 3; i++) {
            Certificate certificate = new Certificate();
            certificate.setName(faker.company().name());
            // Genera fechas aleatorias
            LocalDate issue = LocalDate.now().minusDays(faker.number().numberBetween(1, 1000));
            LocalDate expiration = issue.plusYears(2);
            certificate.setIssueDate(Date.valueOf(issue));
            certificate.setExpirationDate(Date.valueOf(expiration));

            certificateRepository.save(certificate);
        }
    }
}
