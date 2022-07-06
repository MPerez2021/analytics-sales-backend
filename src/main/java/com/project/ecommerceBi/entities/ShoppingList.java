package com.project.ecommerceBi.entities;

import com.project.ecommerceBi.security.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ShoppingList {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter
    @Setter
    private String id;
    @NotNull
    @Getter @Setter
    private int amount;
    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private Product product;
    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private User client;

    public ShoppingList() {
    }

    public ShoppingList(String id, int amount, Product product, User client) {
        this.id = id;
        this.amount = amount;
        this.product = product;
        this.client = client;
    }
}
