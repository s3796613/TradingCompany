package com.code.company.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Embeddable
public class PackageDetail {

    @ManyToOne
    @JoinColumn(referencedColumnName = "id",nullable=false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private OrderMain order;

    private int quantity;
    private double price;


    public PackageDetail() {
    }

    public PackageDetail(Product product, int quantity, double price,OrderMain order) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderMain getOrder() {
        return order;
    }

    public void setOrder(OrderMain order) {
        this.order = order;
    }
}
