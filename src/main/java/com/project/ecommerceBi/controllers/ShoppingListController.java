package com.project.ecommerceBi.controllers;

import com.project.ecommerceBi.dtos.Message;
import com.project.ecommerceBi.entities.ShoppingList;
import com.project.ecommerceBi.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shoppingList")
@CrossOrigin(origins = "*")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;
    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/{client_id}")
    public ResponseEntity<List<ShoppingList>> getListByClient(@PathVariable String client_id){
        return new ResponseEntity<>(this.shoppingListService.getListByClient(client_id), HttpStatus.OK);
    }
    @GetMapping("/count/{client_id}")
    public ResponseEntity<Long> countByClientId(@PathVariable String client_id){
        return new ResponseEntity<>(this.shoppingListService.countByClientId(client_id),HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Object> addProduct(@Valid @RequestBody ShoppingList shoppingList, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise los datos"),HttpStatus.BAD_REQUEST);
        this.shoppingListService.addProduct(shoppingList);
        return new ResponseEntity<>(new Message("Agregado"),HttpStatus.OK);
    }
    @DeleteMapping("/clean/{client_id}")
    public ResponseEntity<Message> cleanShoppingList(@PathVariable String client_id){
        this.shoppingListService.cleanClientShoppingList(client_id);
        return new ResponseEntity<>(new Message("Lista vaciada correctamente"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteProductOfList(@PathVariable String id){
        this.shoppingListService.deleteById(id);
        return new ResponseEntity<>(new Message("Eliminado"), HttpStatus.OK);
    }
}

