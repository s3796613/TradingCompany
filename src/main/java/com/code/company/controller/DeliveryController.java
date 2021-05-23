package com.code.company.controller;

import com.code.company.entity.DeliveryNote;
import com.code.company.entity.PackageDetail;
import com.code.company.entity.ReceivingNote;
import com.code.company.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("delivery")
public class DeliveryController {


    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public Page<DeliveryNote> getAll(Pageable pageable) {
        return deliveryService.findAll(pageable);
    }

    @GetMapping(path = "{id}")
    public DeliveryNote getById(@PathVariable("id") Long id) throws Exception {
        return deliveryService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody DeliveryNote deliveryNote) {
        deliveryService.add(deliveryNote);
    }

    @PutMapping("{id}")
    public void save(@PathVariable("id") Long id,
                     @RequestBody(required = false) DeliveryNote updated) throws Exception {
        deliveryService.update(id,updated);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        deliveryService.delete(id);
    }
}
