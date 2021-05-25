package com.code.company.JPA;

import com.code.company.entity.Customer;
import com.code.company.entity.DeliveryNote;
import com.code.company.entity.SaleInvoice;
import com.code.company.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, Long> {
    @Query("select s from Staff s where s.id =?1")
    Optional<Staff> getStaffByID(Long id);

    @Query("select c from Customer c where c.id=?1")
    Optional<Customer> getCustomerByID(Long id);

    @Query("select d from DeliveryNote d where d.id=?1")
    Optional<DeliveryNote> getDeliveryNote(Long deliveryID);

    Page<SaleInvoice> getSaleInvoiceByCustomerIDAndStaffIDAndDateBetween(Long customerID,Long staffID,LocalDate start, LocalDate end, Pageable pageable);
    Page<SaleInvoice> getSaleInvoiceByCustomerIDAndDateBetween(Long customerID, LocalDate start, LocalDate end, Pageable pageable);
    Page<SaleInvoice> getSaleInvoiceByStaffIDAndDateBetween(Long staffID,LocalDate start, LocalDate end, Pageable pageable);

    Page<SaleInvoice> findByDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
