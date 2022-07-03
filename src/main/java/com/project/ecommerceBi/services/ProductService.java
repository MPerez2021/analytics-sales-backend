package com.project.ecommerceBi.services;

import com.project.ecommerceBi.entities.Product;
import com.project.ecommerceBi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
        this.productRepository.save(product);
    }

    public void editProduct(String productId, Product product){
        Product productToEdit = this.getProductById(productId).get();
        productToEdit.setCategory(product.getCategory());
        productToEdit.setDescription(product.getDescription());

       // productToEdit.setPrice(Double.valueOf(decimalFormat.format(product.getPrice())));
        productToEdit.setPrice(product.getPrice());
        productToEdit.setImage(product.getImage());
        productToEdit.setName(product.getName());
        this.productRepository.save(productToEdit);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId) {
        return this.productRepository.findById(productId);
    }

    public List<Product> getProductFromLessPrice(){
        return this.productRepository.findFirst6ByOrderByPriceAsc();
    }

    public List<Product> getProductsByCategory(String category){
        return this.productRepository.findFirst6AndByCategory(category);
    }

    public List<Product> getRelatedProductsByCategory(String category, String productId){
        List<Product> productsFound = this.productRepository.findByCategoryAndIdNot(category, productId);
        List<Product> randomProducts = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 2 ; i++) {
            int randomIndex = random.nextInt(productsFound.size());
            randomProducts.add(productsFound.get(randomIndex));
            productsFound.remove(randomIndex);
        }
        return randomProducts;
    }
}
