package com.project.ecommerceBi.controllers;

import com.project.ecommerceBi.dtos.Message;
import com.project.ecommerceBi.entities.Product;
import com.project.ecommerceBi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable String productId){
        Optional<Product> product = this.productService.getProductById(productId);
        if (product.isEmpty())
            return new ResponseEntity<>(new Message("Producto no encontrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/create")
    public ResponseEntity<Message> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Datos incorrectos"), HttpStatus.BAD_REQUEST);

        this.productService.createProduct(product);
        return new ResponseEntity<>(new Message("Producto creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{productId}")
    public ResponseEntity<Message> getProductById(@PathVariable String productId, @RequestBody Product product){
        this.productService.editProduct(productId, product);
        return new ResponseEntity<>(new Message("Producto actualizado"), HttpStatus.OK);
    }

}
