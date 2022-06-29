package com.project.ecommerceBi.repositories;

import com.project.ecommerceBi.entities.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail, String> {
    List<Detail> findBySales_Id(String saleId);
}
