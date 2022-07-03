package com.project.ecommerceBi.repositories;

import com.project.ecommerceBi.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, String> {

   List<Sales> findByClient_Id(String clientId);

}
