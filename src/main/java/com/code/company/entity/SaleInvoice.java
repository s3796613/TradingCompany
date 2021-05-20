package com.code.company.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class SaleInvoice{
    @Id
    @SequenceGenerator(
            name = "invoice_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_sequence"
    )
    private Long id;
    private LocalDate date;
    private String staffName;
    private String customerName;
    @ElementCollection
    private List<SaleDetail> saleDetails;

    public SaleInvoice() {
    }

    public SaleInvoice(LocalDate date, String staffName, String customerName, List<SaleDetail> saleDetails) {
        this.date = date;
        this.staffName = staffName;
        this.customerName = customerName;
        this.saleDetails = saleDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<SaleDetail> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetail> saleDetails) {
        this.saleDetails = saleDetails;
    }
}
