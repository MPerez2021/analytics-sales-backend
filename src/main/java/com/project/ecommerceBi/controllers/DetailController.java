package com.project.ecommerceBi.controllers;

import com.project.ecommerceBi.dtos.Message;
import com.project.ecommerceBi.entities.Detail;
import com.project.ecommerceBi.entities.Product;
import com.project.ecommerceBi.security.entities.User;
import com.project.ecommerceBi.security.services.UserService;
import com.project.ecommerceBi.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saleDetail")
@CrossOrigin(origins = "*")
public class DetailController {

    private final DetailService detailService;
    private final UserService userService;

    @Autowired
    public DetailController(DetailService detailService, UserService userService) {
        this.detailService = detailService;
        this.userService = userService;
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<Object> getDetailBySaleId(@PathVariable String saleId) {
        List<Detail> details = this.detailService.getDetailBySaleId(saleId);
        if (details.isEmpty())
            return new ResponseEntity<>(new Message("Aún no tienes compras realizadas"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
