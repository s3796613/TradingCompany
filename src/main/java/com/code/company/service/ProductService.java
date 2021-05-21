package com.code.company.service;

import com.code.company.JPA.ProductRepository;
import com.code.company.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findById(Long id) {
        return productRepository.findProductById(id);
    }

    public void add(Product newProduct) {
        productRepository.save(newProduct);
    }

    public void update(Long id, Product newProduct) throws Exception {
        productRepository.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setModel(newProduct.getModel());
            product.setBrand(newProduct.getBrand());
            product.setCompany(newProduct.getCompany());
            product.setCategory(newProduct.getCategory());
            product.setDescription(newProduct.getDescription());
            product.setPrice(newProduct.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new Exception("Product with id " + id + " is not found!"));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
