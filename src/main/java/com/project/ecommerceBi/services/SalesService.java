package com.project.ecommerceBi.services;

import com.project.ecommerceBi.dtos.AddedToCar;
import com.project.ecommerceBi.entities.Detail;
import com.project.ecommerceBi.entities.Product;
import com.project.ecommerceBi.entities.Sales;
import com.project.ecommerceBi.entities.ShoppingList;
import com.project.ecommerceBi.repositories.SalesRepository;
import com.project.ecommerceBi.security.entities.User;
import com.project.ecommerceBi.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Sales> getByClientId(String clientId) {
        return this.salesRepository.findByClient_Id(clientId);
    }

    public void createSale(String user_mail) {
        User user = this.userService.getByUserEmail(user_mail).get();
        List<ShoppingList> products = this.shoppingListService.getListByClientMail(user.getEmail());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
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
}
