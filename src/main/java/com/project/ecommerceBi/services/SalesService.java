package com.project.ecommerceBi.services;

import com.project.ecommerceBi.dtos.AddedToCar;
import com.project.ecommerceBi.entities.Detail;
import com.project.ecommerceBi.entities.Product;
import com.project.ecommerceBi.entities.Sales;
import com.project.ecommerceBi.repositories.SalesRepository;
import com.project.ecommerceBi.security.entities.User;
import com.project.ecommerceBi.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalesService {

    private final SalesRepository salesRepository;
    private final UserService userService;
    private final DetailService detailService;

    @Autowired
    public SalesService(SalesRepository salesRepository, UserService userService, DetailService detailService) {
        this.salesRepository = salesRepository;
        this.userService = userService;
        this.detailService = detailService;
    }

    public void createSale(List<AddedToCar> products){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = userDetails.getUsername();
        User user = this.userService.getByUserEmail(userEmail).get();
        double total = products.stream().mapToDouble(product -> product.getProduct().getPrice() * product.getAmount()).sum();
        Sales sales = new Sales(total, new Date(), user);
        Sales sales1 = this.salesRepository.save(sales);
        for (int i = 0; i < products.size(); i++) {
            Detail detail1 = new Detail();
            detail1.setProduct(products.get(i).getProduct());
            detail1.setAmount(products.get(i).getAmount());
            detail1.setSales(sales1);
            this.detailService.createDetail(detail1);
        }
    }
}
