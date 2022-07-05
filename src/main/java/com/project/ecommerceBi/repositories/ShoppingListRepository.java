package com.project.ecommerceBi.repositories;

import com.project.ecommerceBi.entities.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, String> {
    List<ShoppingList> findByClient_Id(String clientId);
    void deleteByClient_Id(String clientId);
    Long countByClient_Id(String clientId);
    void deleteById(String id);
}
