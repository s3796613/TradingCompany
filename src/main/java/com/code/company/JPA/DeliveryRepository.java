package com.code.company.JPA;

import com.code.company.entity.DeliveryNote;
import com.code.company.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryNote, Long> {
}
