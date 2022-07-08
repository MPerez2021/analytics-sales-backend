package com.project.ecommerceBi.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class SalesPerMonth {


    @Getter @Setter
    public String month;
    @Getter @Setter
    public int clothes;
    @Getter @Setter
    public int technology;

    public SalesPerMonth() {
    }

    public SalesPerMonth(String month, int clothes, int technology) {
        this.month = month;
        this.clothes = clothes;
        this.technology = technology;
    }
}

