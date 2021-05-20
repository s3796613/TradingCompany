package com.code.company.entity;

import javax.persistence.Embeddable;

@Embeddable
public class SaleDetail extends PackageDetail{
    private Double totalValue;
    public SaleDetail(){
        this.totalValue= this.getQuantity() *this.getPrice();
    };

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
