package com.code.company.entity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Order extends BaseEntity{
    private LocalDate date;
//    private Staff staff;
//    private Provider provider;
//    private List<PackageDetail> packageDetails;
}
