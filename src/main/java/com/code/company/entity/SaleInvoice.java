package com.code.company.entity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class SaleInvoice extends BaseEntity{
    private LocalDate date;
    private String staffName;
    private String customerName;
//    private List<SaleDetail> saleDetails;

}
