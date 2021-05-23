package com.code.company.controller;

import com.code.company.entity.Category;
import com.code.company.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<Category> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @PostMapping
    public void add(@RequestBody Category newCategory) {
        categoryService.add(newCategory);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestParam("name") String name) throws Exception {
        categoryService.update(id,name);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

}
