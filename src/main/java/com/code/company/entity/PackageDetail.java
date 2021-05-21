package com.code.company.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class PackageDetail {
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Product product;
    private int quantity;
    private double price;

    public PackageDetail() {
    }

    public PackageDetail(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = this.product.getPrice();
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
}
