package com.code.company.JPA;

import com.code.company.entity.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryNote, Long> {
    List<DeliveryNote> findByDateBetween(LocalDate start, LocalDate end);
}
