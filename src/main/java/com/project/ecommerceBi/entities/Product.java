package com.project.ecommerceBi.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter @Setter
    private String id;

    @NotBlank
    @NotNull
    @Getter @Setter
    private String name;

    @DecimalMin(value = "0.1")
    @NotNull
    @Getter @Setter
    private Double price;

    @NotBlank
    @NotNull
    @Getter @Setter
    private String description;

    @NotBlank
    @NotNull
    @Getter @Setter
    private String category;


    @NotBlank
    @NotNull
    @Getter @Setter
    private String image;

    public Product() {
    }

    public Product(String id, String name, Double price, String description, String category, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
    }
}
