package com.code.company.entity;

import javax.persistence.Entity;

@Entity
public class Product extends BaseEntity {
    private String name;
    private String model;
    private String brand;
    private String company;
    private String description;
    private Double price;
}
