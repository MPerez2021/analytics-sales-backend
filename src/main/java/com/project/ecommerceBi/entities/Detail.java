package com.project.ecommerceBi.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Detail {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter @Setter
    private String id;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private Product product;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private Sales sales;

    @NotNull
    @Getter @Setter
    private int amount;

    public Detail() {
    }

    public Detail(String id, Product product, Sales sales, int amount) {
        this.id = id;
        this.product = product;
        this.sales = sales;
        this.amount = amount;
    }
}
