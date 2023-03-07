package com.example.demo.Buisness.Services.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.Dao.interfaces.ProductDao;
import java.util.*;
import com.example.demo.Repository.Entities.Product;

@Service
public class ProductService {
    
    @Autowired    
    private ProductDao productDao;

    public String getAllProduct(){
        return "nice";
    }


    public Optional <Product> getByID(int id){
        return productDao.findById(id);
    }


    

}
