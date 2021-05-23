package com.code.company.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class DeliveryNote {
    @Id
    @SequenceGenerator(
            name = "deli_note_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "deli_note_sequence"
    )
    private Long id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Staff staff;

    @ElementCollection
    private List<PackageDetail> packageDetails;


    public DeliveryNote() {
    }

    public DeliveryNote(LocalDate date, Staff staff) {
        this.date = date;
        this.staff = staff;
    }

    public DeliveryNote(LocalDate date, Staff staff, List<PackageDetail> packageDetails) {
        this.date = date;
        this.staff = staff;
        this.packageDetails = packageDetails;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<PackageDetail> getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(List<PackageDetail> packageDetails) {
        this.packageDetails = packageDetails;
    }

}
