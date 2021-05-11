package com.code.company.JPA;

import com.code.company.entity.ReceivingNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingRepository extends JpaRepository<ReceivingNote, Long> {
}
