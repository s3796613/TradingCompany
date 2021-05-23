package com.code.company.controller;

import com.code.company.entity.DeliveryNote;
import com.code.company.entity.OrderMain;
import com.code.company.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("delivery")
public class DeliveryController {


    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
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
                     @RequestBody DeliveryNote updated) throws Exception {
        deliveryService.update(id,updated);
    }
}
