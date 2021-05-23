package com.code.company.JPA;

import com.code.company.entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderMain, Long> {
    Optional<Page<OrderMain>> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
