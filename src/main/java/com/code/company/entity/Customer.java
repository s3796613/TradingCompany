package com.code.company.entity;

import javax.persistence.Entity;

@Entity
public class Customer extends BaseEntity {
    private String name;
    private String address;
    private String email;
    private String phone;
    private String fax;

    public Customer(String name, String address, String email, String phone, String fax) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.fax = fax;
    }

    public Customer() {

    }
}
