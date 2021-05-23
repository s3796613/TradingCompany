package com.code.company.controller;

import com.code.company.entity.ReceivingNote;
import com.code.company.service.ReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("receive")
public class ReceivingController {
    private ReceivingService receivingService;

    @Autowired
    public ReceivingController(ReceivingService receivingService) {
        this.receivingService = receivingService;
    }

    @GetMapping
    public Page<ReceivingNote> getAll(Pageable pageable) {
        return receivingService.findAll(pageable);
    }

    @GetMapping(path = "{id}")
    public ReceivingNote getById(@PathVariable("id") Long id) throws Exception {
        return receivingService.findById(id);
    }

    @PostMapping
    public String add(@RequestBody ReceivingNote receivingNote) throws Exception {
        return receivingService.add(receivingNote);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestParam(required = false) Long staffID,
                       @RequestParam(required = false) Long orderID,
                       @RequestParam(required = false) String date) throws Exception {
        receivingService.update(id,staffID,orderID,date);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) {
        receivingService.delete(id);
    }

    @GetMapping(path = "find")
    public Page<ReceivingNote> find (@RequestParam(required = false) String startDate,
                                     @RequestParam(required = false) String endDate,
                                     Pageable pageable) throws Exception {
        return receivingService.find(startDate,endDate,pageable);
    }


}
