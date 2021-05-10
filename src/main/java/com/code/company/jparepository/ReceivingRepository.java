package com.code.company.jparepository;

import com.code.company.entity.ReceivingNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivingRepository extends JpaRepository<ReceivingNote, Long> {
}
