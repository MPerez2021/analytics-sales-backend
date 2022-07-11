package com.project.ecommerceBi.services;

import com.project.ecommerceBi.dtos.SalesChart;
import com.project.ecommerceBi.dtos.SalesPerMonth;
import com.project.ecommerceBi.entities.Detail;
import com.project.ecommerceBi.entities.Sales;
import com.project.ecommerceBi.entities.ShoppingList;
import com.project.ecommerceBi.repositories.SalesRepository;
import com.project.ecommerceBi.security.entities.User;
import com.project.ecommerceBi.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

@Service
@Transactional
public class SalesService {

    private final SalesRepository salesRepository;
    private final UserService userService;
    private final DetailService detailService;
    private final ShoppingListService shoppingListService;

    @Autowired
    public SalesService(SalesRepository salesRepository, UserService userService, DetailService detailService, ShoppingListService shoppingListService) {
        this.salesRepository = salesRepository;
        this.userService = userService;
        this.detailService = detailService;
        this.shoppingListService = shoppingListService;
    }

    public List<Sales> getAllSales() {
        return this.salesRepository.findAll();
    }

    public List<Sales> getByClientMail(String email) {
        return this.salesRepository.findByClient_Email(email);
    }

    public void createSale(String user_mail) {
        User user = this.userService.getByUserEmail(user_mail).get();
        List<ShoppingList> products = this.shoppingListService.getListByClientMail(user.getEmail());
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        double total = products.stream().mapToDouble(product -> product.getProduct().getPrice() * product.getAmount()).sum();
        Sales sales = new Sales(Double.parseDouble(decimalFormat.format(total)), new Date(), user);
        Sales sales1 = this.salesRepository.save(sales);
        for (int i = 0; i < products.size(); i++) {
            Detail detail1 = new Detail();
            detail1.setProduct(products.get(i).getProduct());
            detail1.setAmount(products.get(i).getAmount());
            detail1.setSales(sales1);
            this.detailService.createDetail(detail1);
        }
        this.shoppingListService.cleanClientShoppingList(user.getId());
    }

    public HashMap<String, Integer> getTotalSalesByCategory() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        //List<SalesChart> salesCharts = new ArrayList<>();
            /* SalesChart salesChart = new SalesChart();
        salesChart.setName("Ropa");
        salesChart.setTotalSales(clothes);
        salesCharts.add(salesChart);
        SalesChart technologyData = new SalesChart();
        technologyData.setCategory("Tecnología");
        technologyData.setTotalSales(technology);
        salesCharts.add(technologyData);*/
        List<SalesChart> categories = this.salesRepository.getSalesByCategory();
        for (SalesChart category : categories) {
            map.put(category.getName(), category.getTotalSales());
        }

        return map;
    }

    public List<SalesPerMonth> getTotalSalesPerMonthAndCategory() {
        List<SalesPerMonth> salesPerMonths = new ArrayList<>();
        List<SalesPerMonth> test = this.salesRepository.getSalesPerMonthAndCategory();
        for (int i = 0; i < test.size(); i++) {
            SalesPerMonth sales = new SalesPerMonth();
            sales.setMonth(test.get(i).getMonth());
            sales.setClothes(test.get(i).getClothes());
            sales.setTechnology(test.get(i).getTechnology());
            salesPerMonths.add(sales);
        }
        return salesPerMonths;

    }

    public HashMap<String, Integer> getTotalSalesByMonth() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        List<SalesChart> salesCharts = this.salesRepository.getSalesPerMonth();
        for (SalesChart salesChart : salesCharts) {
            map.put(salesChart.getName(), salesChart.getTotalSales());
        }
        return map;
    }

    public HashMap<String, Integer> getMostSoldProducts() {
        HashMap<String, Integer> productsMap = new HashMap<String, Integer>();
        List<SalesChart> products = this.salesRepository.getMostSoldProducts();
        for (SalesChart product : products) {
            productsMap.put(product.getName(), product.getTotalSales());
        }
        return productsMap;
    }

    public HashMap<String, Integer> getLeastSoldProducts() {
        HashMap<String, Integer> productsMap = new HashMap<String, Integer>();
        List<SalesChart> products = this.salesRepository.getLeastSoldProducts();
        for (SalesChart product : products) {
            productsMap.put(product.getName(), product.getTotalSales());
        }
        return productsMap;
    }


}
