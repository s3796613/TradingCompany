package com.code.company.service;

import com.code.company.JPA.CustomerRepository;
import com.code.company.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }



    public Page<Customer> getAll(Optional<Integer> page) {
        return repository.findAll(PageRequest.of(page.orElse(0),20));
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

    public Page<Customer> find( Optional<String> name, Optional<String> address, Optional<String> phone, Pageable pageable) {
        if (name.isPresent()) {
            return repository.findByNameContains(name.orElse(""),pageable);
        }
        if (address.isPresent()) {
            return repository.findByAddressContains(address.orElse(""),pageable);
        }
        if (phone.isPresent()) {
            return repository.findByPhone(phone.orElse(""), pageable);
        }
        return repository.findAll(pageable);
    }
}
