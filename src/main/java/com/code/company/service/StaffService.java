package com.code.company.service;

import com.code.company.JPA.SaleInvoiceRepository;
import com.code.company.JPA.StaffRepository;
import com.code.company.entity.SaleInvoice;
import com.code.company.entity.Staff;
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
        return staffRepository.findStaffById(id);
    }

    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }

    public void add(Staff staff) {
        staffRepository.save(staff);
    }

    @Transactional
    public void update(Long id, String name, String address, String email, String phone) throws Exception {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new Exception("Staff id not found"));
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
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        Pageable pageable = PageRequest.of(0,20);

        List<SaleInvoice> invoices = staffRepository.getStaffSaleInvoice(id);
        List<SaleInvoice> filtered = new ArrayList<>();
        for (SaleInvoice invoice : invoices) {
            if (invoice.getDate().isAfter(start) && invoice.getDate().isBefore(end)) {
                filtered.add(invoice);
            }
        }
        return new PageImpl<>(filtered,pageable,filtered.size());
    }
}
