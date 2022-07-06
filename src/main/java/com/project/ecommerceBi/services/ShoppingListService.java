package com.project.ecommerceBi.services;

import com.project.ecommerceBi.entities.ShoppingList;
import com.project.ecommerceBi.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }
    public List<ShoppingList> getListByClient(String clientId){
        return this.shoppingListRepository.findByClient_Id(clientId);
    }
    public List<ShoppingList> getListByClientMail(String clientMail){
        return this.shoppingListRepository.findByClient_Email(clientMail);
    }
    public void cleanClientShoppingList(String clientId){
        this.shoppingListRepository.deleteByClient_Id(clientId);
    }
    public void deleteById(String id){
        this.shoppingListRepository.deleteById(id);
    }
    public void addProduct(ShoppingList shoppingList){ this.shoppingListRepository.save(shoppingList);}
    public Long countByClientId(String clientId){
        return this.shoppingListRepository.countByClient_Id(clientId);
    }
}
