package com.code.company.controller;

import com.code.company.entity.Storage;
import com.code.company.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("storage")
public class StorageController {
    private StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public Storage getInventory() {
        return storageService.getInventory();
    }


    @GetMapping(path = "find")
    public Storage getInventoryByDate(@RequestParam(required = false) String start,
                                      @RequestParam(required = false) String end) {
        return storageService.getInventoryByDate(start,end);
    }
}
