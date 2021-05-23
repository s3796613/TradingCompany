package com.code.company.service;

import com.code.company.JPA.CategoryRepository;
import com.code.company.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void update(Long id, String newCategory) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Category with id " + id + " is not found!"));
        category.setName(newCategory);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
