package com.project.ecommerceBi.services;

import com.project.ecommerceBi.entities.Detail;
import com.project.ecommerceBi.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public List<Detail> getDetailBySaleId(String saleId){
        return this.detailRepository.findBySales_Id(saleId);
    }
}
