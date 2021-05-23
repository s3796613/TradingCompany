package com.code.company.service;

import com.code.company.JPA.SaleInvoiceRepository;
import com.code.company.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return saleInvoiceRepository.findAll(pageable);
    }

    public SaleInvoice getById(Long id) throws Exception {
        return saleInvoiceRepository.findById(id).orElseThrow(() -> new Exception("Sale invoice id not found!"));
    }

    public String add(SaleInvoice saleInvoice) throws Exception {
        Staff staff =saleInvoiceRepository.getStaffByID(saleInvoice.getStaffID()).orElseThrow(() -> new Exception("Staff id not found"));
        saleInvoice.setStaffName(staff.getName());
        saleInvoice.setSaleDetails(getDeliveryData(saleInvoice.getDeliveryID()));
        saleInvoiceRepository.save(saleInvoice);
        return "Sale invoice created! " + saleInvoice.toString();
    }

    public void delete(Long id) {
        saleInvoiceRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Long staffID, Long deliveryID, String date) throws Exception {
        SaleInvoice saleInvoice = saleInvoiceRepository.findById(id).orElseThrow(() -> new Exception("Sale invoice id not found"));
        if (staffID != null && staffID > 0) {
            Staff staff = saleInvoiceRepository.getStaffByID(staffID).orElseThrow(() -> new Exception("Staff id not found"));
            saleInvoice.setStaffID(staff.getId());
            saleInvoice.setStaffName(staff.getName());
        }
        if (deliveryID != null && deliveryID > 0) {
            saleInvoice.setSaleDetails(getDeliveryData(deliveryID));
            saleInvoice.setDeliveryID(deliveryID);
        }
        if (date != null && date.length() > 0) {
            saleInvoice.setDate(LocalDate.parse(date));
        }
    }



    //Data handling
    private List<SaleDetail> getDeliveryData(Long deliveryID) throws Exception {
        DeliveryNote deliveryNote = saleInvoiceRepository.getDeliveryNote(deliveryID).orElseThrow(() -> new Exception("Delivery id not found"));
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


}
