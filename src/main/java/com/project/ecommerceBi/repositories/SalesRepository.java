package com.project.ecommerceBi.repositories;

import com.project.ecommerceBi.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, String> {
}
