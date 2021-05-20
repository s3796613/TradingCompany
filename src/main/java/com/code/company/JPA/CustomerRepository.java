package com.code.company.JPA;

import com.code.company.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerById(Long id);
    Page<Customer> findByNameContains(String name, Pageable pageable);
    Page<Customer> findByAddressContains(String address, Pageable pageable);
    Page<Customer> findByPhone(String phone, Pageable pageable);
}
