package com.code.company.entity;

import com.code.company.jparepository.CustomerRepository;
import com.code.company.jparepository.ProviderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class Commandline {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args -> {
            Customer victor = new Customer("12312","1231","123","3213","321");

            Customer june = new Customer("321321","321321","2313","321321","3123");
            repository.saveAll(List.of(victor,june));
        };
    }

    @Bean
    CommandLineRunner commandLineRunner2(ProviderRepository pRepo) {
        return args -> {
            Provider p1 = new Provider("231","3213","3213","3123","1233");
            pRepo.saveAll(List.of(p1));
        };
    }
}
