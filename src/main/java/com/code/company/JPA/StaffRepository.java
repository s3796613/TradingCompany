package com.code.company.JPA;

import com.code.company.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findStaffById(Long id);
    Page<Staff> findByName(String name, Pageable pageable);
    Page<Staff> findByAddress(String address, Pageable pageable);
    Page<Staff> findByPhone(String phone, Pageable pageable);
}
