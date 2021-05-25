package com.code.company.service;

import com.code.company.JPA.StaffRepository;
import com.code.company.controller.exception.NoResult;
import com.code.company.controller.exception.NotFound;
import com.code.company.entity.SaleInvoice;
import com.code.company.entity.Staff;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import java.sql.SQLTransactionRollbackException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    //CRUD

    public Page<Staff> getAll(Optional<Integer> page) {
        return staffRepository.findAll(PageRequest.of(page.orElse(0),20));
    }

    public Staff getById(Long id) {
        Staff staff = staffRepository.findStaffById(id);
        if (staff == null) {
            throw new NotFound();
        } else return staff;
    }


    @Transactional
    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }

    public void add(Staff staff) {
        staffRepository.save(staff);
    }


    @Transactional
    public void update(Long id, String name, String address, String email, String phone) throws Exception {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new NotFound("Staff id not found"));
        if (name != null && name.length() > 0) {
            staff.setName(name);
        }
        if (address != null && address.length() > 0) {
            staff.setAddress(address);
        }
        if (email != null && email.length() > 0) {
            staff.setEmail(email);
        }
        if (phone != null && phone.length() > 0) {
            staff.setPhone(phone);
        }

    }

    //Search api

    public Page<Staff> find(Optional<String> name, Optional<String> address, Optional<String> phone, Pageable pageable) {
        if (name.isPresent()) {
            return staffRepository.findByNameContains(name.orElse(""),pageable);
        }
        if (address.isPresent()) {
            return staffRepository.findByAddressContains(address.orElse(""),pageable);
        }
        if (phone.isPresent()) {
            return staffRepository.findByPhone(phone.orElse(""), pageable);
        }
        return staffRepository.findAll(pageable);
    }


    //Get sales
    public Page<SaleInvoice> getStaffSale(Long id, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate).minusDays(1);
        LocalDate end = LocalDate.parse(endDate).plusDays(1);
        Pageable pageable = PageRequest.of(0,20);

        List<SaleInvoice> invoices = staffRepository.getStaffSaleInvoice(id);
        List<SaleInvoice> filtered = new ArrayList<>();
        for (SaleInvoice invoice : invoices) {
            if (invoice.getDate().isAfter(start) && invoice.getDate().isBefore(end)) {
                filtered.add(invoice);
            }
        }
        if (filtered.isEmpty()) {
            throw new NoResult("No sale found between " + startDate + " - " + endDate);
        }
        return new PageImpl<>(filtered,pageable,filtered.size());
    }
}
