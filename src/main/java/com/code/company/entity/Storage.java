package com.code.company.entity;

import java.util.List;

public class Storage {
    private List<Inventory> inventoryList;

    public Storage() {
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
