package com.code.company.controller;

import com.code.company.entity.SaleInvoice;
import com.code.company.service.SaleInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sale")
public class SaleInvoiceController {
    private SaleInvoiceService saleInvoiceService;

    @Autowired
    public SaleInvoiceController(SaleInvoiceService saleInvoiceService) {
        this.saleInvoiceService = saleInvoiceService;
    }

    @GetMapping
    public Page<SaleInvoice> getAll(Pageable pageable) {
        return saleInvoiceService.findAll(pageable);
    }

    @GetMapping(path = "{id}")
    public SaleInvoice getById(@PathVariable("id") Long id) throws Exception {
        return saleInvoiceService.getById(id);
    }

    @PostMapping
    public String add(@RequestBody SaleInvoice saleInvoice) throws Exception {
        return saleInvoiceService.add(saleInvoice);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestParam(required = false) Long staffID,
                       @RequestParam(required = false) Long deliveryID,
                       @RequestParam(required = false) Long customerID,
                       @RequestParam(required = false) String date) throws Exception {
        saleInvoiceService.update(id,staffID,deliveryID,customerID,date);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) {
        saleInvoiceService.delete(id);
    }

    @GetMapping(path = "find")
    public Page<SaleInvoice> find(@RequestParam("start") String start,
                                  @RequestParam("end") String end,
                                  Pageable pageable) {
        return saleInvoiceService.find(start,end,pageable);
    }
    @GetMapping(path = "find")
    public Page<SaleInvoice> find(@RequestParam("customerID") Long customerID,
                                  @RequestParam("staffID") Long staffID,
                                  @RequestParam("start") String start,
                                  @RequestParam("end") String end,
                                  Pageable pageable) {
        return saleInvoiceService.getSaleInvoicesByCustomerAndStaff(customerID,staffID,start,end,pageable);
    }
}
