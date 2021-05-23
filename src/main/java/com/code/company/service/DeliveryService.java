package com.code.company.service;

import com.code.company.JPA.DeliveryRepository;
import com.code.company.entity.DeliveryNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    //CRUD

    public Page<DeliveryNote> findAll(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }

    public DeliveryNote findById(Long id) throws Exception {
        return deliveryRepository.findById(id).orElseThrow(()-> new Exception("Delivery note not found!"));
    }

    public void add(DeliveryNote deliveryNote) {
        deliveryRepository.save(deliveryNote);
    }

    public void update(Long id, DeliveryNote updated) throws Exception{
    }

    //Search api

}
