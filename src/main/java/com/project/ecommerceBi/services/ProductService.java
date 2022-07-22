package com.project.ecommerceBi.services;

import com.project.ecommerceBi.entities.Product;
import com.project.ecommerceBi.repositories.ProductRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
        productToEdit.setPrice(product.getPrice());
        productToEdit.setImage(product.getImage());
        productToEdit.setName(product.getName());
        this.productRepository.save(productToEdit);
    }

    public void uploadCsvFile(MultipartFile file) throws IOException {
        List<Product> productsToUpload = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Product product = new Product();
            product.setName(record.getString("name"));
            product.setPrice(Double.parseDouble(record.getString("price")));
            product.setDescription(record.getString("description"));
            product.setCategory(record.getString("category"));
            product.setImage(record.getString("image"));
            productsToUpload.add(product);
        });
        this.productRepository.saveAll(productsToUpload);
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
        return this.productRepository.findByCategory(category);
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
