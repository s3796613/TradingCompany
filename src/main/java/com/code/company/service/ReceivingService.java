package com.code.company.service;

import com.code.company.JPA.ReceivingRepository;
import com.code.company.controller.exception.NoResult;
import com.code.company.controller.exception.NotFound;
import com.code.company.entity.OrderMain;
import com.code.company.entity.PackageDetail;
import com.code.company.entity.ReceivingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceivingService {
    private final ReceivingRepository receivingRepository;

    @Autowired
    public ReceivingService(ReceivingRepository receivingRepository) {
        this.receivingRepository = receivingRepository;
    }

    //CRUD
    public Page<ReceivingNote> findAll(Pageable pageable) {
        return receivingRepository.findAll(pageable);
    }

    public ReceivingNote findById(Long id) throws Exception {
        return receivingRepository.findById(id).orElseThrow(() -> new NotFound("ReceivingNote's id not found!"));
    }

    public String add(ReceivingNote receivingNote) throws Exception {
        receivingRepository.staffData(receivingNote.getStaff().getId()).orElseThrow(() -> new NotFound("Staff id not found"));
        receivingNote.setReceivingDetails(getOrderData(receivingNote.getOrderID()));
        receivingRepository.save(receivingNote);
        return "Create receiving note successfully with id " + receivingNote.getId() + ", orderID: " + receivingNote.getOrderID();
    }

    public void delete(Long id) {
        receivingRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Long staffID, Long orderID, String date) throws Exception {
        ReceivingNote receivingNote = receivingRepository.findById(id).orElseThrow(() -> new NotFound("Receiving note id not found"));
        if (staffID != null && staffID > 0) {
            receivingNote.setStaff(receivingRepository.staffData(staffID).orElseThrow(() -> new NotFound("Staff id not found")));
        }

        if (orderID != null && orderID > 0) {
            receivingNote.setReceivingDetails(getOrderData(orderID));
            receivingNote.setOrderID(orderID);
        }

        if (date != null) {
            receivingNote.setDate(LocalDate.parse(date));
        }
    }

    //Method for getting order details by orderID
    private List<PackageDetail> getOrderData(Long orderID) throws Exception {
        OrderMain orderMain = receivingRepository.orderData(orderID).orElseThrow(() -> new Exception("Order id not found"));
        List<PackageDetail> retrievedData = orderMain.getPackageDetails();
        List<PackageDetail> newData = new ArrayList<>();
        for (PackageDetail p: retrievedData) {
            PackageDetail newPackage = new PackageDetail();
            newPackage.setProduct(p.getProduct());
            newPackage.setQuantity(p.getQuantity());
            newPackage.setPrice(p.getPrice());
            newData.add(newPackage);
        }
        return newData;
    }



    //search api
    public Page<ReceivingNote> find(String sDate, String eDate, Pageable pageable ) throws Exception {
        LocalDate startDate = LocalDate.parse(sDate);
        LocalDate endDate = LocalDate.parse(eDate);
        return receivingRepository.findByDateBetween(startDate,endDate,pageable).orElseThrow(() -> new NoResult("Didnot find any note"));
    }
}
