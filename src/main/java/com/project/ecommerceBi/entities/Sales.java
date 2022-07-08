package com.project.ecommerceBi.entities;

import com.project.ecommerceBi.dtos.SalesChart;
import com.project.ecommerceBi.dtos.SalesPerMonth;
import com.project.ecommerceBi.security.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Sales.getSalesPerMonthAndCategory",
                query = "SELECT * FROM sales_per_month_category",
                resultSetMapping = "Mapping.SalesPerMonthAndCategory"),
        @NamedNativeQuery(
                name = "Sales.getSalesByCategory",
                query = "SELECT * FROM sales_by_category",
                resultSetMapping = "Mapping.SalesByCategory"),
        @NamedNativeQuery(
                name = "Sales.getSalesPerMonth",
                query = "SELECT * FROM total_sales_per_month",
                resultSetMapping = "Mapping.SalesPerMonth"),
        @NamedNativeQuery(
                name = "Sales.getMostSoldProducts",
                query = "SELECT * FROM most_sold_products",
                resultSetMapping = "Mapping.MostSoldProducts"),
        @NamedNativeQuery(
                name = "Sales.getLeastSoldProducts",
                query = "SELECT * FROM least_sold_products",
                resultSetMapping = "Mapping.LeastSoldProducts")
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "Mapping.SalesPerMonthAndCategory",
                classes = @ConstructorResult(targetClass = SalesPerMonth.class,
                        columns = {@ColumnResult(name = "month"),
                                @ColumnResult(name = "clothes"),
                                @ColumnResult(name = "technology")})),
        @SqlResultSetMapping(name = "Mapping.SalesByCategory",
                classes = @ConstructorResult(targetClass = SalesChart.class,
                        columns = {@ColumnResult(name = "name"),
                                @ColumnResult(name = "totalSales")})),
        @SqlResultSetMapping(name = "Mapping.SalesPerMonth",
                classes = @ConstructorResult(targetClass = SalesChart.class,
                        columns = {@ColumnResult(name = "name"),
                                @ColumnResult(name = "totalSales")})),
        @SqlResultSetMapping(name = "Mapping.MostSoldProducts",
                classes = @ConstructorResult(targetClass = SalesChart.class,
                        columns = {@ColumnResult(name = "name"),
                                @ColumnResult(name = "totalSales")})),
        @SqlResultSetMapping(name = "Mapping.LeastSoldProducts",
                classes = @ConstructorResult(targetClass = SalesChart.class,
                        columns = {@ColumnResult(name = "name"),
                                @ColumnResult(name = "totalSales")})),
})


@Entity
public class Sales {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter
    @Setter
    private String id;

    @NotNull
    @Getter
    @Setter
    private Double total;

    @NotNull
    @Getter
    @Setter
    @Column(columnDefinition = "DATE")
    private Date date;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private User client;

    public Sales() {
    }

    public Sales(String id, Double total, Date date, User client) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.client = client;
    }

    public Sales(Double total, Date date, User client) {
        this.total = total;
        this.date = date;
        this.client = client;
    }
}
