package com.code.company.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Provider {
    @Id
    @SequenceGenerator(
            name = "provider_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "provider_sequence"
    )
    @Column(name = "id")
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String fax;
    private String contactPerson;

    public Provider() {
    }

    @OneToMany
    private List<OrderMain> orderMain;

    public Provider(String name, String address, String email, String phone, String fax, String contactPerson) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.fax = fax;
        this.contactPerson = contactPerson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}
