package com.project.ecommerceBi.repositories;

import com.project.ecommerceBi.dtos.SalesChart;
import com.project.ecommerceBi.dtos.SalesPerMonth;
import com.project.ecommerceBi.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, String> {

    List<Sales> findByClient_Email(String clientMail);

    @Query(nativeQuery = true)
    List<SalesPerMonth> getSalesPerMonthAndCategory();

    @Query(nativeQuery = true)
    List<SalesChart> getSalesByCategory();

    @Query(nativeQuery = true)
    List<SalesChart> getSalesPerMonth();

    @Query(nativeQuery = true)
    List<SalesChart> getMostSoldProducts();

    @Query(nativeQuery = true)
    List<SalesChart> getLeastSoldProducts();

}
