package com.code.company.service;

import com.code.company.JPA.CustomerRepository;
import com.code.company.controller.exception.NoResult;
import com.code.company.controller.exception.NotFound;
import com.code.company.entity.Customer;
import com.code.company.entity.SaleInvoice;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    //crud

    public Page<Customer> getAll(Optional<Integer> page) {
        return repository.findAll(PageRequest.of(page.orElse(0),20));
    }

    public Customer getById(Long id) {
        Customer customer = repository.findCustomerById(id);
        if (customer == null) {
            throw new NotFound("Did not find any customer with the id: " + id);
        }
        return customer;
    }

    public void deleteById(Long id) {
        if (repository.getSaleInvoice(id).isEmpty()) {
            repository.deleteById(id);
        }
        throw new NotFound("This customer have some invoice in the database");
    }

    public void add(Customer customer) {
        repository.save(customer);
    }


    @Transactional
    public void update(Long id, String name, String address, String email, String phone, String fax, String contactPerson) throws Exception {
        Customer customer = repository.findById(id).orElseThrow(() -> new NotFound("Customer id not found"));
        if (name != null && name.length() > 0) {
            customer.setName(name);
        }
        if (address != null && address.length() > 0) {
            customer.setAddress(address);
        }
        if (email != null && email.length() > 0) {
            customer.setEmail(email);
        }
        if (phone != null && phone.length() > 0) {
            customer.setPhone(phone);
        }
        if (fax != null && fax.length() > 0) {
            customer.setFax(fax);
        }
        if (contactPerson != null && contactPerson.length() > 0) {
            customer.setContactPerson(contactPerson);
        }
    }

    //search api
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

    public Page<SaleInvoice> findSaleInvoice(Long id, String start, String end) {
        List<SaleInvoice> invoices = repository.getSaleInvoiceByID(id);
        LocalDate startDate = LocalDate.parse(start).minusDays(1);
        LocalDate endDate = LocalDate.parse(end).plusDays(1);
        Pageable pageable = PageRequest.of(0,20);
        List<SaleInvoice> filtered = new ArrayList<>();
        for (SaleInvoice invoice : invoices) {
            if (invoice.getDate().isAfter(startDate) && invoice.getDate().isBefore(endDate)) {
                filtered.add(invoice);
            }
        }
        if (filtered.isEmpty()) {
            throw new NoResult("No sale found between " + start + " - " +end);
        }
        return new PageImpl<>(filtered,pageable,filtered.size());
    }
}
