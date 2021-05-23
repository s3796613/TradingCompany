package com.code.company.controller;

import com.code.company.entity.Customer;
import com.code.company.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //CRUD api

    //Get all with pagination
    @GetMapping
    public Page<Customer> getAll(@RequestParam Optional<Integer> page) {
        return customerService.getAll(page);
    }

    @GetMapping(path = "{id}")
    public Customer getById(@PathVariable("id") Long id) {
        return customerService.getById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") Long id) {
        customerService.deleteById(id);
    }

    @PostMapping
    public void add(@RequestBody Customer customer) {
        customerService.add(customer);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) String address,
                       @RequestParam(required = false) String email,
                       @RequestParam(required = false) String phone,
                       @RequestParam(required = false) String fax,
                       @RequestParam(required = false) String contactPerson) throws Exception {
        customerService.update(id,name,address,email,phone,fax,contactPerson);
    }


    //Search api with pagination
    @GetMapping(path = "find")
    public Page<Customer> find(@RequestParam(required = false) Optional<String> name,
                               @RequestParam(required = false) Optional<String> address,
                               @RequestParam(required = false) Optional<String> phone,
                               Pageable pageable) {
        return customerService.find(name,address,phone,pageable);
    }

}
