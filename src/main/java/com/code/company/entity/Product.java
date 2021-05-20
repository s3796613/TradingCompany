package com.code.company.entity;

import javax.persistence.*;

@Entity
public class Product{
    @Id
    @SequenceGenerator(
            name = "product_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String name;
    private String model;
    private String brand;
    private String company;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Category category;
    private String description;
    private Double price;

    public Product() {

    }

    public Product(String name, String model, String brand, String company, Category category, String description, Double price) {
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.company = company;
        this.category = category;
        this.description = description;
        this.price = price;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
