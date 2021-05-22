package com.code.company.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class OrderMain {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Provider provider;

    @ElementCollection
    private List<PackageDetail> packageDetails;

    public OrderMain() {
    }

    public OrderMain(Staff staff, Provider provider) {
        this.staff = staff;
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
