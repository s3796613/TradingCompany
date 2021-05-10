package com.code.company.jparepository;

import com.code.company.entity.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryNote, Long> {
}
