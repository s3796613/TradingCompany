package com.code.company.service;

import com.code.company.entity.Customer;
import com.code.company.JPA.CustomerRepository;
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

    public Customer getById(Long id) {
        return repository.findCustomerById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void add(Customer customer) {
        repository.save(customer);
    }

    public void update(Long id, Customer newCustomer) throws Exception {
        repository.findById(id).map(customer -> {
            customer.setName(newCustomer.getName());
            customer.setAddress(newCustomer.getAddress());
            customer.setEmail(newCustomer.getEmail());
            customer.setPhone(newCustomer.getPhone());
            customer.setFax(newCustomer.getFax());
            customer.setContactPerson(newCustomer.getContactPerson());
            return repository.save(customer);
        }).orElseThrow(() -> new Exception("Customer not found"));
    }

    public List<Customer> find(String name, String address, String phone) {
        return null;
    }
}
