package com.code.company.service;

import com.code.company.JPA.StaffRepository;
import com.code.company.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    public List<Staff> getAll() {
        return staffRepository.findAll();
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
}
