package com.code.company.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ReceivingNote{
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
//    private Staff staff;
//    private List<PackageDetail> receivingDetails;

}
