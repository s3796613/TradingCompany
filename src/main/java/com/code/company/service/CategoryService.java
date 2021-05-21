package com.code.company.service;

import com.code.company.JPA.CategoryRepository;
import com.code.company.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public void add(Category newCategory) {
        categoryRepository.save(newCategory);
    }

    public void update(Long id, Category newCategory) throws Exception {
        categoryRepository.findById(id).map(category -> {
            category.setName(newCategory.getName());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new Exception("Category with id " + id + " is not found!"));
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
