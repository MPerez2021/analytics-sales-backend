package com.project.ecommerceBi.services;

import com.project.ecommerceBi.entities.Detail;
import com.project.ecommerceBi.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetailService {

    private final DetailRepository detailRepository;

    @Autowired
    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public void createDetail(Detail detail){
        this.detailRepository.save(detail);
    }

    public List<Detail> getDetailByCategoryName(String categoryName){
        return this.detailRepository.findByProduct_Category(categoryName);
    }

    public List<Detail> getDetailBySaleId(String saleId){
        return this.detailRepository.findBySales_Id(saleId);
    }

    public int getDetailByCategory(String categoryName){
        return this.detailRepository.countByProduct_Category(categoryName);
    }
}
