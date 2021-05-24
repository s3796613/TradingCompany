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
            Customer c1 = new Customer("Diago Trade", "30 Silk Road", "diagotrade@gmail.com", "0937421321", "123341245", "ThangNe");
            Customer c2 = new Customer("Jack's Whole Sale", "42 Green Waverley", "jackws@gmail.com", "07936482", "12346714", "JackSe");
            Customer c3 = new Customer("Lucy Blakeslee", "89 Loftsgordon Pass", "lblakeslee0@csmonitor.com", "275 892 7686", "4759866691", "Lucylu");
            Customer c4 = new Customer("Brandice Ricker", "5 Tennessee Park", "bricker1@unesco.org", "276 638 1016", "1386033533", "Brand");
            Customer c5 = new Customer("Hershel Bener", "10 Toban Circle", "hbener2@fastcompany.com", "965 773 0766", "5468382983", "Hershine");

            customerRepository.saveAll(List.of(c1, c2, c3, c4, c5));

            //Load provider
            Provider p1 = new Provider("Lee Sin", "12 Blair Rd", "lee@gmail.com", "0192384214", "1233555", "Lie");
            Provider p2 = new Provider("Victor Supplies", "20 Nguyen Van Linh", "vsup@gmail.com", "02937741", "123456", "Vicitor");
            Provider p3 = new Provider("Jillayne Giovanitti", "88223 Mifflin Trail", "jgiovanitti3@hp.com", "455 831 6096", "1147157058", "Jiillayne");
            Provider p4 = new Provider("Flss Martynov", "6 Hooker Point", "fmartynov4@over-blog.com", "703 983 4176", "2728249789", "Fliss");
            Provider p5 = new Provider("Kathye Prujean", "2 Elka Crossing", "kprujean5@telegraph.co.uk", "856 970 7335", "6853013507", "Kathiye");
            pRepo.saveAll(List.of(p1, p2, p3, p4, p5));

            //Load staff
            Staff s1 = new Staff("Jane", "Distric 7", "jane@hotmail.com", "0928361");
            Staff s2 = new Staff("Jack", "23 Hung Vuong", "jack@edu.com", "092713");
            Staff s3 = new Staff("Alysia Scopham", "56168 7th Street", "ascopham6@plala.or.jp", "128 581 2103");
            Staff s4 = new Staff("Cornie Kalf", "01 Michigan Crossing", "ckalf7@nytimes.com", "134 899 3532");
            Staff s5 = new Staff("Pauletta Como", "67778 Del Sol Plaza", "pcomo8@so-net.ne.jp", "958 169 1256");
            staffRepository.saveAll(List.of(s1, s2, s3, s4, s5));

            //Load category
            Category cat1 = new Category("Medicine");
            Category cat2 = new Category("Food");
            Category cat3 = new Category("Drink");
            Category cat4 = new Category("Fruit");
            categoryRepository.saveAll(List.of(cat1, cat2, cat3,cat4));


            //Load product
            Product product1 = new Product(
                    "Vitamin E",
                    "12HAC",
                    "Daily's",
                    "DonyJ Corp", cat1,
                    "Vitamin E for daily use",
                    10.99);
            Product product2 = new Product(
                    "Longos - Chicken Wings", "YWO2", "Ropinirole Hydrochloride", "Hauck Inc", cat2, "In congue. Etiam justo. Etiam pretium iaculis justo.", 96.35);
            Product product3 = new Product(
                    "Spinach - Frozen", "ZJV4", "Estarylla", "Lynch LLC", cat2, "Duis bibendum, felis sed interdum venenatis, turpis enim blandit m.", 9.57);
            Product product4 = new Product(
                    "Tea - English Breakfast", "VBE1", "Prialt", "Mayer, Bergstrom and Shields", cat3, "Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.", 39.47);
            Product product5 = new Product(
                    "Peach - Halves", "VUJ0", "Caladryl Clear", "Grady-Beahan", cat4, "Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.", 16.35);
            productRepository.saveAll(List.of(product1, product2, product3, product4, product5));


            //Load order

            //Package details
            PackageDetail packageDetail1 = new PackageDetail(product1, 4);
            PackageDetail packageDetail2 = new PackageDetail(product2, 2);
            List<PackageDetail> packageDetailList = new ArrayList<>();
            packageDetailList.add(packageDetail1);
            packageDetailList.add(packageDetail2);

            List<PackageDetail> packageDetailList2 = new ArrayList<>();
            packageDetailList2.add(packageDetail2);

            OrderMain o1 = new OrderMain();
            o1.setProvider(p1);
            LocalDate date = LocalDate.of(2021,5,24);
            o1.setDate(date);
            o1.setStaff(s1);
            o1.setPackageDetails(packageDetailList);

            OrderMain o2 = new OrderMain();
            o2.setProvider(p2);
            o2.setDate(date);
            o2.setStaff(s2);
            o2.setPackageDetails(packageDetailList2);

            orderRepository.saveAll(List.of(o1, o2));

//            Load Receiving
            ReceivingNote r1 = new ReceivingNote();
            r1.setStaff(s2);
            r1.setOrderID(1L);
            r1.setDate(date);
            r1.setReceivingDetails(packageDetailList);

            ReceivingNote r2 = new ReceivingNote();
            r2.setStaff(s1);
            r2.setOrderID(2L);
            r2.setDate(date);
            r2.setReceivingDetails(packageDetailList2);
            receivingRepository.saveAll(List.of(r1,r2));


            //Load Delivery

            DeliveryNote d1 = new DeliveryNote(date, s1);
            d1.setPackageDetails(packageDetailList);
            DeliveryNote d2 = new DeliveryNote(date, s2);
            d1.setPackageDetails(packageDetailList2);
            deliveryRepository.saveAll(List.of(d1,d2));


        };
    }
}
