package com.project.ecommerceBi.controllers;

import com.project.ecommerceBi.dtos.AddedToCar;
import com.project.ecommerceBi.dtos.Message;
import com.project.ecommerceBi.entities.Product;
import com.project.ecommerceBi.entities.Sales;
import com.project.ecommerceBi.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "*")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createSale(@RequestBody List<AddedToCar> products){
        this.salesService.createSale(products);

        return new ResponseEntity<>(new Message("Venta genereada"), HttpStatus.OK);
    }
}
