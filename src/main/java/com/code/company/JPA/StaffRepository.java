package com.code.company.JPA;

import com.code.company.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findStaffById(Long id);
    Page<Staff> findByNameContains(String name, Pageable pageable);
    Page<Staff> findByAddressContains(String address, Pageable pageable);
    Page<Staff> findByPhone(String phone, Pageable pageable);

    Optional<Staff> findStaffByEmail(String email);
    Optional<Staff> findStaffByPhone(String phone);
}
