package com.code.company.entity;

import javax.persistence.Embeddable;

@Embeddable
public class SaleDetail extends PackageDetail{
    private Double totalValue;
}
