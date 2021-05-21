package com.code.company.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderMain {
    @Id
    @SequenceGenerator(
            name = "order_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;
    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Provider provider;

    @ElementCollection
    private List<PackageDetail> packageDetails;


    public OrderMain() {
    }

    public OrderMain(String text, Staff staff, Provider provider) {
        this.text = text;
        this.staff = staff;
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<PackageDetail> getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(List<PackageDetail> packageDetails) {
        this.packageDetails = packageDetails;
    }
}
