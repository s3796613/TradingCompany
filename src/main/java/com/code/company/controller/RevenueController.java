package com.code.company.controller;

import com.code.company.entity.Revenue;
import com.code.company.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("revenue")
public class RevenueController {
    private RevenueService revenueService;

    @Autowired
    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping(path = "customer/{id}")
    public Revenue getCustomerRevenue(@PathVariable("id") Long id,
                              @RequestParam("start") String start,
                              @RequestParam("end") String end) throws Exception {
        return revenueService.getCustomerRevenue(id,start,end);
    }

    @GetMapping(path = "staff/{id}")
    public Revenue getStaffRevenue(@PathVariable("id") Long id,
                                   @RequestParam("start") String start,
                                   @RequestParam("end") String end) throws Exception {
        return revenueService.getStaffRevenue(id,start,end);
    }
}
