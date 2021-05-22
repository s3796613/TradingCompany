package com.code.company.JPA;

import com.code.company.entity.OrderMain;
import com.code.company.entity.ReceivingNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceivingRepository extends JpaRepository<ReceivingNote, Long> {
    Optional<ReceivingNote> findReceivingNoteByOrder(OrderMain order);
}
