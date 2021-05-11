package com.code.company.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class SaleInvoice{
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String staffName;
    private String customerName;
//    private List<SaleDetail> saleDetails;

}
