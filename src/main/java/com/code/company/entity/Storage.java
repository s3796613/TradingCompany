package com.code.company.entity;

import java.time.LocalDate;
import java.util.List;

public class Storage {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Inventory> inventoryList;

    public Storage() {
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
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
}
