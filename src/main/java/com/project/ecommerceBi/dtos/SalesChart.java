package com.project.ecommerceBi.dtos;

import lombok.Getter;
import lombok.Setter;

public class SalesChart {

    @Getter @Setter
    public String name;

    @Getter @Setter
    public int totalSales;


    public SalesChart(String name, int totalSales) {
        this.name = name;
        this.totalSales = totalSales;
    }
}
