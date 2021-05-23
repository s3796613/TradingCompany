package com.code.company.service;

import com.code.company.JPA.OrderRepository;
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
        return orderRepository.findAll(pageable);
    }

    public OrderMain findById(Long id) throws Exception {
        return orderRepository.findById(id).orElseThrow(()-> new Exception("Order not found!"));
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
        }).orElseThrow(() -> new Exception("Order id not found!"));
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    //Search api
    public Page<OrderMain> find(String startDate, String endDate, Pageable pageable) throws Exception {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return orderRepository.findByDateBetween(start,end,pageable).orElseThrow(() -> new Exception("Didn't find any order"));
    }
}
