package com.project.ecommerceBi.dtos;

import com.project.ecommerceBi.entities.Product;
import lombok.Getter;
import lombok.Setter;

public class AddedToCar {

    @Getter @Setter
    public Product product;

    @Getter @Setter
    public int amount;
}
