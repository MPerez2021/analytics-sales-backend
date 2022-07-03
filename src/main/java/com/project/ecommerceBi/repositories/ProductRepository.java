package com.project.ecommerceBi.repositories;

import com.project.ecommerceBi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findFirst6ByOrderByPriceAsc();
    List<Product> findFirst6AndByCategory(String category);
    List<Product> findByCategoryAndIdNot(String category, String productId);
}
