package com.code.company.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ReceivingNote{
    @Id
    @SequenceGenerator(
            name = "recv_note_generator"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recv_note_generator"
    )
    private Long id;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Staff staff;


    @Column(nullable = false)
    private Long orderID;

    @ElementCollection
    private List<PackageDetail> receivingDetails = new ArrayList<>();

    public ReceivingNote() {
    }

    public ReceivingNote(LocalDate date, Staff staff, Long orderID) {
        this.date = date;
        this.staff = staff;
        this.orderID = orderID;
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

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public List<PackageDetail> getReceivingDetails() {
        return receivingDetails;
    }

    public void setReceivingDetails(List<PackageDetail> receivingDetails) {
        this.receivingDetails = receivingDetails;
    }


}
