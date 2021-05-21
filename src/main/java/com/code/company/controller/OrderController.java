package com.code.company.controller;

import com.code.company.entity.OrderMain;
import com.code.company.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<OrderMain> getAll(Pageable pageable) {
        return orderService.findAll(pageable);
    }

    @GetMapping(path = "{id}")
    public OrderMain getById(@PathVariable("id") Long id) throws Exception {
        return orderService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody OrderMain orderMain) {
        orderService.add(orderMain);
    }

    @PutMapping("{id}")
    public void save(@PathVariable("id") Long id,
                     @RequestBody OrderMain updated) throws Exception {
        orderService.update(id,updated);
    }
}
