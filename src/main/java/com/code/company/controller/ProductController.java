package com.code.company.controller;

import com.code.company.entity.Product;
import com.code.company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //CRUD

    @GetMapping
    public Page<Product> getAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping(path = "{id}")
    public Product findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody Product newProduct) {
        productService.add(newProduct);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestBody Product newProduct) throws Exception {
        productService.update(id,newProduct);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

}
