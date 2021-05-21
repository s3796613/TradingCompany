package com.code.company.controller;

import com.code.company.entity.Provider;
import com.code.company.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("provider")
public class ProviderController {
    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    //CRUD

    @GetMapping
    public Page<Provider> getAll(Pageable pageable) {
        return providerService.findAll(pageable);
    }

    @GetMapping(path = "{id}")
    public Provider getById(@PathVariable("id") Long id) {
        return providerService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody Provider provider) {
        providerService.add(provider);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestBody Provider newProvider) throws Exception {
        providerService.update(id,newProvider);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) {
        providerService.delete(id);
    }

    //Search API with pagination
    @GetMapping(path = "find")
    public Page<Provider> find(@RequestParam(required = false) Optional<String> name,
                               @RequestParam(required = false) Optional<String> address,
                               @RequestParam(required = false) Optional<String> phone,
                               Pageable pageable) {
        return providerService.find(name,address,phone,pageable);
    }

}
