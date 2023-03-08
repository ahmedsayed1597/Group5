package com.example.demo.buisness.services.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.buisness.exceptions.ProductNotFoundException;
import com.example.demo.repository.dao.interfaces.ProductDao;
import com.example.demo.repository.entities.Product;

import java.util.*;

@Service
public class ProductService {
    
    @Autowired    
    private ProductDao productDao;

    public String getAllProduct(){
        return "nice";
    }


    public Product getByID(int id){
        return productDao.findById(id).orElseThrow(()->new ProductNotFoundException("no such product"));
    }


    

}
