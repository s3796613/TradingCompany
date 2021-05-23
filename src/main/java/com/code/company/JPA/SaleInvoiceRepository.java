package com.code.company.JPA;

import com.code.company.entity.DeliveryNote;
import com.code.company.entity.SaleInvoice;
import com.code.company.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, Long> {
    @Query("select s from Staff s where s.id =?1")
    Optional<Staff> getStaffByID(Long id);

    @Query("select d from DeliveryNote d where d.id=?1")
    Optional<DeliveryNote> getDeliveryNote(Long deliveryID);
}
