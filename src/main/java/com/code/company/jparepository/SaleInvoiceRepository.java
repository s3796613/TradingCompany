package com.code.company.jparepository;

import com.code.company.entity.SaleInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, Long> {
}
