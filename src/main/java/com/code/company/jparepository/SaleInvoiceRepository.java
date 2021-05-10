package com.code.company.jparepository;

import com.code.company.entity.SaleInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, Long> {
}
