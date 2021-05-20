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
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Staff staff;
    @ElementCollection
    private List<PackageDetail> deliveryDetails;

    public DeliveryNote() {
    }

    public DeliveryNote(LocalDate date, Staff staff, List<PackageDetail> deliveryDetails) {
        this.date = date;
        this.staff = staff;
        this.deliveryDetails = deliveryDetails;
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

    public List<PackageDetail> getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(List<PackageDetail> deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }
}
