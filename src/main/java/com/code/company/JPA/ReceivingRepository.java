package com.code.company.JPA;

import com.code.company.entity.OrderMain;
import com.code.company.entity.PackageDetail;
import com.code.company.entity.ReceivingNote;
import com.code.company.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReceivingRepository extends JpaRepository<ReceivingNote, Long> {
    Optional<ReceivingNote> findReceivingNoteByOrder(OrderMain order);
    @Query("select o from OrderMain o where o.id =?1")
    Optional<OrderMain> orderData(Long id);

    @Query("select s from Staff s where s.id =?1")
    Optional<Staff> staffData(Long id);

    Optional<Page<ReceivingNote>> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
