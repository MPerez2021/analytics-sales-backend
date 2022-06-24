package com.project.ecommerceBi.repositories;

import com.project.ecommerceBi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
