package com.code.company.entity;

import javax.persistence.*;
import java.time.LocalDate;

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

    @OneToOne
    @JoinColumn(name="id", nullable=false)
    private OrderMain order;

    public DeliveryNote() {
    }

    public DeliveryNote(LocalDate date, Staff staff, OrderMain order) {
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
