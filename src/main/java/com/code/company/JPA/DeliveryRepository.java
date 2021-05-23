package com.code.company.JPA;

import com.code.company.entity.DeliveryNote;
import com.code.company.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryNote, Long> {
    List<DeliveryNote> findByDateBetween(LocalDate start, LocalDate end);

    Page<DeliveryNote> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
