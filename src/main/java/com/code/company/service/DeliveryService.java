package com.code.company.service;

import com.code.company.JPA.DeliveryRepository;
import com.code.company.controller.exception.NoResult;
import com.code.company.controller.exception.NotFound;
import com.code.company.entity.DeliveryNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
        return deliveryRepository.findById(id).orElseThrow(()-> new NotFound("Delivery note not found!"));
    }

    public void add(DeliveryNote deliveryNote) {
        deliveryRepository.save(deliveryNote);
    }

    @Transactional
    public void update(Long id, DeliveryNote updated) throws Exception{
        deliveryRepository.findById(id).map(deliveryNote -> {
            deliveryNote.setDate(updated.getDate());
            deliveryNote.setStaff(updated.getStaff());
            deliveryNote.setPackageDetails(updated.getPackageDetails());
            return deliveryRepository.save(deliveryNote);
        }).orElseThrow(() -> new NotFound("Delivery id not found!"));
    }

    public void delete(Long id) {
        deliveryRepository.deleteById(id);
    }


    //Search api
    public Page<DeliveryNote> find(String start, String end, Pageable pageable) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        Page<DeliveryNote> data = deliveryRepository.findByDateBetween(startDate,endDate,pageable);
        if (data.isEmpty()) {
            throw new NoResult("No result found between " + start + " - " +end);
        }
        return data;
    }


}
