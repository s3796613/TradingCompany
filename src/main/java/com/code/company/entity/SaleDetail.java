package com.code.company.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class SaleDetail {
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Product product;
    private int quantity;
    private double price;
    private double totalValue;

    public SaleDetail() {
    }

    public SaleDetail(Product product, int quantity, double price, double totalValue) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.totalValue = totalValue;
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

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}
