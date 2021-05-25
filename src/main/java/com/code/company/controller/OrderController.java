package com.code.company.controller;

import com.code.company.entity.OrderMain;
import com.code.company.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public String add(@RequestBody OrderMain orderMain) {
        orderService.add(orderMain);
        return "Create Order successfully!";
    }

    @PutMapping("{id}")
    public void save(@PathVariable("id") Long id,
                     @RequestBody OrderMain updated) throws Exception {
        orderService.update(id,updated);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }

    @GetMapping(path = "find")
    public Page<OrderMain> find(@RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate,
                                Pageable pageable) throws Exception {
        return orderService.find(startDate,endDate,pageable);
    }
}
