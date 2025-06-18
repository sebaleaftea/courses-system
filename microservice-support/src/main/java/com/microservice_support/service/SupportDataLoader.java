package com.microservice_support.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.concurrent.ThreadLocalRandom;

import com.microservice_support.model.Support;
import com.microservice_support.repository.ISupportRepository;

@Profile("test")
@Component
public class SupportDataLoader implements CommandLineRunner {

    @Autowired
    private ISupportRepository supportRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    private final String[] statuses = {"pendiente", "en proceso", "resuelto"};

    @Override
    public void run(String... args) {
        for (int i = 0; i < 30; i++) {
            String ticketCode = generateTicketCode(i);
            String status = statuses[random.nextInt(statuses.length)];

            Support support = Support.builder()
                    .ticket(ticketCode)
                    .status(status)
                    .build();

            supportRepository.save(support);
        }
    }

    private String generateTicketCode(int index) {
        return String.format("C-%03d", index + 1); // e.g., C-001, C-002, ...
    }
}
