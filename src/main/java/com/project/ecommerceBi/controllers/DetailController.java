package com.project.ecommerceBi.controllers;

import com.project.ecommerceBi.entities.Detail;
import com.project.ecommerceBi.security.services.UserService;
import com.project.ecommerceBi.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
