package com.code.company.service;

import com.code.company.JPA.StaffRepository;
import com.code.company.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    public Page<Staff> getAll(Optional<Integer> page) {
        return staffRepository.findAll(PageRequest.of(page.orElse(0),2));
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

    public void update(Long id, Staff newStaff) throws Exception {
        staffRepository.findById(id).map(staff -> {
            staff.setName(newStaff.getName());
            staff.setAddress(newStaff.getAddress());
            staff.setEmail(newStaff.getEmail());
            staff.setPhone(newStaff.getPhone());
            return staffRepository.save(staff);
        }).orElseThrow(() -> new Exception("Staff not found!"));
    }

    public Page<Staff> find(Optional<String> name, Optional<String> address, Optional<String> phone, Pageable pageable) {
        if (name.isPresent()) {
            return staffRepository.findByName(name.orElse(""),pageable);
        }
        if (address.isPresent()) {
            return staffRepository.findByAddress(address.orElse(""),pageable);
        }
        if (phone.isPresent()) {
            return staffRepository.findByPhone(phone.orElse(""), pageable);
        }
        return staffRepository.findAll(pageable);
    }
}
