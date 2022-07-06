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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;
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

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllSales(){
        List<Sales> sales =this.salesService.getAllSales();
        if (sales.isEmpty())
            return new ResponseEntity<>(new Message("Aún no se ha generado ninguna venta"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Object> getByClientId(@PathVariable String clientId) {
        List<Sales> sales = this.salesService.getByClientId(clientId);
        if (sales.isEmpty())
            return new ResponseEntity<>(new Message("Aún no has realizado ninguna compra"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

        @PostMapping("/create/{user_mail}")
    public ResponseEntity<Message> createSale(@PathVariable String user_mail){
        this.salesService.createSale(user_mail);
        return new ResponseEntity<>(new Message("Venta genereada"), HttpStatus.OK);
    }
}
