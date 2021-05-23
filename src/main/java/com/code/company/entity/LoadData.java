package com.code.company.entity;

import com.code.company.JPA.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadData {
    @Bean
    CommandLineRunner commandLineRunner2(ProviderRepository pRepo,
                                         OrderRepository orderRepository,
                                         StaffRepository staffRepository,
                                         ProductRepository productRepository,
                                         CategoryRepository categoryRepository,
                                         CustomerRepository customerRepository,
                                         DeliveryRepository deliveryRepository,
                                         ReceivingRepository receivingRepository) {
        return args -> {
            //Load customer
            Customer c1 = new Customer("Diago Trade","30 Silk Road","diagotrade@gmail.com","0937421321","123341245","Thang");
            Customer c2 = new Customer("Jack's Whole Sale","42 Green Waverley","jackws@gmail.com","07936482","12346714","Jack");
            customerRepository.saveAll(List.of(c1,c2));

            //Load provider
            Provider p1 = new Provider("Lee Sin","12 Blair Rd","lee@gmail.com","0192384214","1233555","Lee");
            Provider p2 = new Provider("Victor Supplies","20 Nguyen Van Linh", "vsup@gmail.com", "02937741", "123456", "Victor");
            pRepo.saveAll(List.of(p1,p2));

            //Load staff
            Staff s1 = new Staff("Jane","Distric 7","jane@hotmail.com","0928361");
            Staff s2 = new Staff("Jack", "23 Hung Vuong","jack@edu.com", "092713");
            staffRepository.saveAll(List.of(s1,s2));

            //Load category
            Category cat1 = new Category("Medicine");
            Category cat2 = new Category("Food");
            categoryRepository.saveAll(List.of(cat1,cat2));


            //Load product
            Product product1 = new Product(
                    "Vitamin E",
                    "12HAC",
                    "Daily's",
                    "DonyJ Corp",cat1,
                    "Vitamin E for daily use",
                    10.99);
            Product product2 = new Product(
                    "Chicken Sausage",
                    "big",
                    "ausFarm",
                    "DonyJ Corp",cat2,
                    "Can be used to make some simple dishes",
                    1.99);
            productRepository.saveAll(List.of(product1,product2));


            //Load order

            //Package details
            PackageDetail packageDetail1 = new PackageDetail(product1,4);
            PackageDetail packageDetail2 = new PackageDetail(product2, 2);
            List<PackageDetail> packageDetailList = new ArrayList<>();
            packageDetailList.add(packageDetail1);
            packageDetailList.add(packageDetail2);

            List<PackageDetail> packageDetailList2 = new ArrayList<>();
            packageDetailList2.add(packageDetail2);

            OrderMain o1 = new OrderMain();
            o1.setProvider(p1);
            o1.setDate(LocalDate.now());
            o1.setStaff(s1);
            o1.setPackageDetails(packageDetailList);

            OrderMain o2 = new OrderMain();
            o2.setProvider(p2);
            o2.setDate(LocalDate.now());
            o2.setStaff(s2);
            o2.setPackageDetails(packageDetailList2);

            orderRepository.saveAll(List.of(o1,o2));

            //Load Receiving
//            ReceivingNote r1 = new ReceivingNote();
//            r1.setStaff(s2);
//            r1.setDate(LocalDate.parse("2020-4-24"));
//            receivingRepository.save(r1);


            //Load Delivery

            DeliveryNote d1 = new DeliveryNote(LocalDate.now(),s1);
            d1.setPackageDetails(packageDetailList);
            deliveryRepository.save(d1);





        };
    }
}
