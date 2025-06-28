package com.microservice_support;

import com.microservice_support.model.Support;
import com.microservice_support.repository.ISupportRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test") // Solo se ejecuta con el perfil 'test'
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ISupportRepository supportRepository;

    @Override
    public void run(String... args) {
        if (supportRepository.count() == 0) {
            Faker faker = new Faker();
            for (int i = 0; i < 5; i++) {
                supportRepository.save(Support.builder()
                        .ticket(faker.lorem().sentence(3))
                        .status(faker.options().option("Pendiente", "En proceso", "Resuelto"))
                        .build());
            }
        }
    }
}