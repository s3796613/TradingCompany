package com.code.company.JPA;

import com.code.company.entity.Category;
import com.code.company.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select c from Category c where c.id=?1")
    Optional<Category> getCategory(Long id);

    Product findProductById(Long id);
}
