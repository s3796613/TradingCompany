package com.code.company.entity;

import java.time.LocalDate;

public class Revenue {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double revenue;

    public Revenue() {
    }

    public Revenue(String name, LocalDate startDate, LocalDate endDate, double revenue) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.revenue = revenue;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
