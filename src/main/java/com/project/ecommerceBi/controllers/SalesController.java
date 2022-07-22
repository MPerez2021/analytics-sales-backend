package com.project.ecommerceBi.controllers;

import com.project.ecommerceBi.dtos.Message;
import com.project.ecommerceBi.entities.Sales;
import com.project.ecommerceBi.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllSales() {
        List<Sales> sales = this.salesService.getAllSales();
        if (sales.isEmpty())
            return new ResponseEntity<>(new Message("Aún no se ha generado ninguna venta"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/client")
    public ResponseEntity<Object> getByClientId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = userDetails.getUsername();
        List<Sales> sales = this.salesService.getByClientMail(userEmail);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PostMapping("/create/{user_mail}")
    public ResponseEntity<Message> createSale(@PathVariable String user_mail) {
        this.salesService.createSale(user_mail);
        return new ResponseEntity<>(new Message("Venta genereada"), HttpStatus.OK);
    }

    @GetMapping("/totalByCategory")
    public ResponseEntity<Object> getTotalSalesByCategory(){
        return new ResponseEntity<>(this.salesService.getTotalSalesByCategory(), HttpStatus.OK);
    }

    @GetMapping("/totalByMonthAndCategory")
    public ResponseEntity<Object> getTotalSalesByMonthAndCategory(){
        return new ResponseEntity<>(this.salesService.getTotalSalesPerMonthAndCategory(), HttpStatus.OK);
    }

    @GetMapping("/totalByMonth")
    public ResponseEntity<Object> getTotalSalesByMonth(){
        return new ResponseEntity<>(this.salesService.getTotalSalesByMonth(), HttpStatus.OK);
    }

    @GetMapping("/mostSoldProducts")
    public ResponseEntity<Object> getMostSoldProducts(){
        return new ResponseEntity<>(this.salesService.getMostSoldProducts(), HttpStatus.OK);
    }

    @GetMapping("/leastSoldProducts")
    public ResponseEntity<Object> getLeastSoldProducts(){
        return new ResponseEntity<>(this.salesService.getLeastSoldProducts(), HttpStatus.OK);
    }
}
