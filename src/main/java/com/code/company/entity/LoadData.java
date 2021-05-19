package com.code.company.entity;

import com.code.company.JPA.CustomerRepository;
import com.code.company.JPA.ProviderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoadData {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args -> {
            Customer c1 = new Customer("Diago Trade","30 Silk Road","diagotrade@gmail.com","0937421321","123341245","Thang");

            Customer c2 = new Customer("Jack's Whole Sale","42 Green Waverley","jackws@gmail.com","07936482","12346714","Jack");
            Customer c3 = new Customer("Jack's Whole Sale","42 Green Waverley","jackws@gmail.com","07936482","12346714","Jack");
            Customer c4 = new Customer("Jack's Whole Sale","42 Green Waverley","jackws@gmail.com","07936482","12346714","Jack");
            repository.saveAll(List.of(c1,c2,c3,c4));
        };
    }

    @Bean
    CommandLineRunner commandLineRunner2(ProviderRepository pRepo) {
        return args -> {
            Provider p1 = new Provider("Lee Sin","12 Blair Rd","lee@gmail.com","0192384214","1233555","Lee");
            pRepo.saveAll(List.of(p1));
        };
    }
}
