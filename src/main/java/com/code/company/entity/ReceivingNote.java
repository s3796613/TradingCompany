package com.code.company.entity;

import javax.persistence.*;
import java.time.LocalDate;
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
    @JoinColumn(referencedColumnName = "id",nullable=false)
    private Staff staff;

    @OneToOne
    @JoinColumn(name="id", nullable=false)
    private OrderMain order;

    @ElementCollection
    private List<PackageDetail> packageDetails;

    public ReceivingNote() {
    }

    public ReceivingNote(LocalDate date, Staff staff, OrderMain order) {
        this.date = date;
        this.staff = staff;
        this.order = order;
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

    public OrderMain getOrder() {
        return order;
    }

    public void setOrder(OrderMain order) {
        this.order = order;
    }
}
