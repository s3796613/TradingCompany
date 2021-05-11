package com.code.company.service;

import com.code.company.entity.Customer;
import com.code.company.jparepository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    public List<Customer> getAll() {
        return repository.findAll();
    }
}
