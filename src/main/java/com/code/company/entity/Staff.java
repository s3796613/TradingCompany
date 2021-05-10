package com.code.company.entity;

import javax.persistence.Entity;

@Entity
public class Staff extends BaseEntity{
    private String name;
    private String address;
    private String email;
    private String phone;
}
