package com.code.company.service;

import com.code.company.JPA.DeliveryRepository;
import com.code.company.entity.DeliveryNote;
import com.code.company.entity.PackageDetail;
import com.code.company.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public void update(Long id, LocalDate newDate, Staff staff, List<PackageDetail> packageDetails) throws Exception{
    }

    public Page<DeliveryNote> findAll(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }
}
