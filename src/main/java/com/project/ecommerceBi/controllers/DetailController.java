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
@RequestMapping("/productDetail")
@CrossOrigin(origins = "*")
public class DetailController {

    private final DetailService detailService;
    private final UserService userService;

    @Autowired
    public DetailController(DetailService detailService, UserService userService) {
        this.detailService = detailService;
        this.userService = userService;
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Object> getDetailByClientId(@PathVariable String clientId) {
        List<Detail> details = this.detailService.getDetailByClientId(clientId);
        if (details.isEmpty())
            return new ResponseEntity<>(new Message("Aún no tienes compras realizadas"), HttpStatus.NOT_FOUND);
       /* List<Object> newArray = new ArrayList<>();
        for (Detail data : details) {
            newArray.add(data.getProduct());
            newArray.add(data.getAmount());
        }*/
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
