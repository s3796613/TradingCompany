package com.code.company.controller;

import com.code.company.entity.Customer;
import com.code.company.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //CRUD api

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
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
                       @RequestBody Customer newCustomer) throws Exception {
        customerService.update(id,newCustomer);
    }

    //Search api

    @GetMapping(path = "find")
    public List<Customer> find(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String address,
                               @RequestParam(required = false) String phone) {
        return customerService.find(name,address,phone);
    }

}
