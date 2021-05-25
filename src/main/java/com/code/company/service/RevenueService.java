package com.code.company.service;

import com.code.company.JPA.CustomerRepository;
import com.code.company.JPA.SaleInvoiceRepository;
import com.code.company.JPA.StaffRepository;
import com.code.company.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RevenueService {
    private SaleInvoiceRepository saleInvoiceRepository;
    private CustomerRepository customerRepository;
    private StaffRepository staffRepository;

    @Autowired
    public RevenueService(SaleInvoiceRepository saleInvoiceRepository, CustomerRepository customerRepository, StaffRepository staffRepository) {
        this.saleInvoiceRepository = saleInvoiceRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }


    public Revenue getCustomerRevenue(Long id, String start, String end) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer id not found"));
        Revenue revenue = new Revenue();
        revenue.setName(customer.getName());
        revenue.setStartDate(LocalDate.parse(start));
        revenue.setEndDate(LocalDate.parse(end));
        List<SaleInvoice> saleInvoiceList = customerRepository.getSaleInvoiceByID(id);
        revenue.setRevenue(getTotalValue(saleInvoiceList, start, end));
        return revenue;
    }

    public Revenue getStaffRevenue(Long id, String start, String end) throws Exception {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new Exception("Staff id not found"));
        Revenue revenue = new Revenue();
        revenue.setName(staff.getName());
        revenue.setStartDate(LocalDate.parse(start));
        revenue.setEndDate(LocalDate.parse(end));
        List<SaleInvoice> saleInvoiceList = staffRepository.getStaffSaleInvoice(id);
        revenue.setRevenue(getTotalValue(saleInvoiceList,start,end));
        return revenue;
    }

    private double getTotalValue(List<SaleInvoice> saleInvoiceList, String startDate, String endDate) {
        double totalValue = 0;
        LocalDate start = LocalDate.parse(startDate).minusDays(1);
        LocalDate end = LocalDate.parse(endDate).plusDays(1);
        for (SaleInvoice saleInvoice : saleInvoiceList) {
            if (saleInvoice.getDate().isAfter(start) && saleInvoice.getDate().isBefore(end)) {
                List<SaleDetail> saleDetails = saleInvoice.getSaleDetails();
                for (SaleDetail detail : saleDetails) {
                    totalValue += detail.getTotalValue();
                }
            }
        }
        return totalValue;
    }
}
