package com.code.company.service;

import com.code.company.JPA.ProductRepository;
import com.code.company.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, String name, String model, String company, String brand, Long categoryID, String description, Double price) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Product id not found"));
        if (name != null && name.length() > 0) {
            product.setName(name);
        }
        if (model != null && model.length() > 0) {
            product.setModel(model);
        }
        if (company != null && company.length() > 0) {
            product.setCompany(company);
        }
        if (brand != null && brand.length() > 0) {
            product.setBrand(brand);
        }
        if (categoryID != null && categoryID > 0) {
            product.setCategory(productRepository.getCategory(categoryID).orElseThrow(() -> new Exception("Category id not found!")));
        }
        if (description != null && description.length() > 0) {
            product.setDescription(description);
        }
        if (price != null && price > 0) {
            product.setPrice(price);
        }
    }
}
