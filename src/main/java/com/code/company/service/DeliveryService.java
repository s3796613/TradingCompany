package com.code.company.service;

import com.code.company.JPA.DeliveryRepository;
import com.code.company.entity.DeliveryNote;
import com.code.company.entity.OrderMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public DeliveryNote findById(Long id) throws Exception {
        return deliveryRepository.findById(id).orElseThrow(()-> new Exception("Delivery note not found!"));
    }

    public void add(DeliveryNote deliveryNote) {
        deliveryRepository.save(deliveryNote);
    }

    public void update(Long id, DeliveryNote updated) throws Exception{
    }
}
