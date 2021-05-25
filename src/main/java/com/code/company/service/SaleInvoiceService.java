package com.code.company.service;

import com.code.company.JPA.SaleInvoiceRepository;
import com.code.company.controller.exception.NoResult;
import com.code.company.controller.exception.NotFound;
import com.code.company.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleInvoiceService {
    private final SaleInvoiceRepository saleInvoiceRepository;

    @Autowired
    public SaleInvoiceService(SaleInvoiceRepository saleInvoiceRepository) {
        this.saleInvoiceRepository = saleInvoiceRepository;
    }


    //CRUD
    public Page<SaleInvoice> findAll(Pageable pageable) {
        Page<SaleInvoice> data = saleInvoiceRepository.findAll(pageable);
        if (data.isEmpty()) {
            throw new NoResult("No sale invoice found");
        }
        return data;
    }

    public SaleInvoice getById(Long id) throws Exception {
        return saleInvoiceRepository.findById(id).orElseThrow(() -> new NotFound("Sale invoice id not found!"));
    }

    @Transactional
    public String add(SaleInvoice saleInvoice) throws Exception {
        Staff staff =saleInvoiceRepository.getStaffByID(saleInvoice.getStaffID()).orElseThrow(() -> new NotFound("Staff id not found"));
        Customer customer = saleInvoiceRepository.getCustomerByID(saleInvoice.getCustomerID()).orElseThrow(() -> new NotFound("Customer id not found"));
        saleInvoice.setCustomerName(customer.getName());
        saleInvoice.setStaffName(staff.getName());
        saleInvoice.setSaleDetails(getDeliveryData(saleInvoice.getDeliveryID()));
        saleInvoiceRepository.save(saleInvoice);
        return "Sale invoice created! id: " + saleInvoice.getId();
    }

    public void delete(Long id) {
        saleInvoiceRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Long staffID, Long deliveryID, Long customerID, String date) throws Exception {
        SaleInvoice saleInvoice = saleInvoiceRepository.findById(id).orElseThrow(() -> new NotFound("Sale invoice id not found"));
        if (staffID != null && staffID > 0) {
            Staff staff = saleInvoiceRepository.getStaffByID(staffID).orElseThrow(() -> new NotFound("Staff id not found"));
            saleInvoice.setStaffID(staff.getId());
            saleInvoice.setStaffName(staff.getName());
        }
        if (deliveryID != null && deliveryID > 0) {
            saleInvoice.setSaleDetails(getDeliveryData(deliveryID));
            saleInvoice.setDeliveryID(deliveryID);
        }
        if (customerID != null && customerID > 0) {
            Customer customer = saleInvoiceRepository.getCustomerByID(customerID).orElseThrow(() -> new NotFound("Customer id not found"));
            saleInvoice.setCustomerID(customer.getId());
            saleInvoice.setCustomerName(customer.getName());
        }
        if (date != null && date.length() > 0) {
            saleInvoice.setDate(LocalDate.parse(date));
        }
    }



    //Data handling
    private List<SaleDetail> getDeliveryData(Long deliveryID) throws Exception {
        DeliveryNote deliveryNote = saleInvoiceRepository.getDeliveryNote(deliveryID).orElseThrow(() -> new NotFound("Delivery id not found"));
        List<PackageDetail> retrievedData = deliveryNote.getPackageDetails();
        List<SaleDetail> newData = new ArrayList<>();
        for (PackageDetail p: retrievedData) {
            SaleDetail newPackage = new SaleDetail();
            newPackage.setProduct(p.getProduct());
            newPackage.setQuantity(p.getQuantity());
            newPackage.setPrice(p.getPrice());
            newPackage.setTotalValue(p.getQuantity() * p.getPrice());
            newData.add(newPackage);
        }
        return newData;
    }

    //Search api
    public Page<SaleInvoice> find(String start, String end, Pageable pageable) {
        LocalDate startD = LocalDate.parse(start);
        LocalDate endD = LocalDate.parse(end);
        return saleInvoiceRepository.findByDateBetween(startD,endD,pageable);
    }

    //Get sales by customer and staff
    public Page<SaleInvoice> getSaleInvoicesByCustomerAndStaff(Long customerID,Long staffID,String startDate, String endDate, Pageable pageable) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        if (customerID != null && staffID != null) {
            return saleInvoiceRepository.getSaleInvoiceByCustomerIDAndStaffIDAndDateBetween(customerID, staffID, start, end, pageable);
        }
        if (customerID != null) {
            return saleInvoiceRepository.getSaleInvoiceByCustomerIDAndDateBetween(customerID, start, end, pageable);
        }
        if (staffID != null) {
            return saleInvoiceRepository.getSaleInvoiceByStaffIDAndDateBetween(staffID, start, end, pageable);
        }
        return saleInvoiceRepository.findByDateBetween(start,end,pageable);

    }



}
