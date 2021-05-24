package com.code.company.service;

import com.code.company.JPA.OrderRepository;
import com.code.company.controller.exception.NoResult;
import com.code.company.controller.exception.NotFound;
import com.code.company.entity.OrderMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //CRUD

    public Page<OrderMain> findAll(Pageable pageable) {
        Page<OrderMain> data = orderRepository.findAll(pageable);
        if (data.isEmpty()) {
            throw new NoResult("No order data found!");
        }
        return data;
    }

    public OrderMain findById(Long id) throws Exception {
        return orderRepository.findById(id).orElseThrow(()-> new NotFound("Order not found!"));
    }

    public void add(OrderMain orderMain) {
        orderRepository.save(orderMain);
    }

    @Transactional
    public void update(Long id, OrderMain updated) throws Exception {
        orderRepository.findById(id).map(orderMain -> {
            orderMain.setDate(updated.getDate());
            orderMain.setStaff(updated.getStaff());
            orderMain.setProvider(updated.getProvider());
            orderMain.setPackageDetails(updated.getPackageDetails());
            return orderRepository.save(orderMain);
        }).orElseThrow(() -> new NotFound("Order id not found!"));
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    //Search api
    public Page<OrderMain> find(String startDate, String endDate, Pageable pageable) throws Exception {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return orderRepository.findByDateBetween(start,end,pageable).orElseThrow(() -> new NoResult("Didn't find any order"));
    }
}
