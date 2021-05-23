package com.code.company.service;

import com.code.company.JPA.DeliveryRepository;
import com.code.company.JPA.ProductRepository;
import com.code.company.JPA.ReceivingRepository;
import com.code.company.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {
    private ProductRepository productRepository;
    private ReceivingRepository receivingRepository;
    private DeliveryRepository deliveryRepository;

    @Autowired
    public StorageService(ProductRepository productRepository, ReceivingRepository receivingRepository, DeliveryRepository deliveryRepository) {
        this.productRepository = productRepository;
        this.receivingRepository = receivingRepository;
        this.deliveryRepository = deliveryRepository;
    }


    public Storage getInventory() {
        Storage storage = new Storage();
        List<Product> productList = productRepository.findAll();
        List<Inventory> inventoryList = new ArrayList<>();
        for (Product product: productList) {
            Inventory inventory = new Inventory();
            inventory.setProductID(product.getId());
            inventory.setProductName(product.getName());
            int receive = getReceivedQuantity(product.getId());
            int delivery = getDeliveryQuantity(product.getId());
            inventory.setReceived(receive);
            inventory.setDelivery(delivery);
            inventory.setBalance(receive - delivery);
            inventoryList.add(inventory);

        }
        storage.setInventoryList(inventoryList);
        return storage;
    }

    private int getReceivedQuantity(Long productID) {
        int quantity = 0;
        List<ReceivingNote> receivingNoteList = receivingRepository.findAll();
        for (ReceivingNote note: receivingNoteList) {
           List<PackageDetail> receivingDetails = note.getReceivingDetails();
            for (PackageDetail pack: receivingDetails) {
                if (productID.equals(pack.getProduct().getId())) {
                    quantity += pack.getQuantity();
                }
            }
        }
        return quantity;
    }

    private int getDeliveryQuantity(Long productID) {
        int quantity = 0;
        List<DeliveryNote> deliveryNoteList = deliveryRepository.findAll();
        for (DeliveryNote note : deliveryNoteList) {
            List<PackageDetail> deliveryDetails = note.getPackageDetails();
            for (PackageDetail pack : deliveryDetails) {
                if (productID.equals(pack.getProduct().getId())) {
                    quantity += pack.getQuantity();
                }
            }
        }
        return quantity;
    }


}
