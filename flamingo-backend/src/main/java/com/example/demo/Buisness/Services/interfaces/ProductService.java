package com.example.demo.buisness.services.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.presentation.responseViewModel.ProductResponse;
import com.example.demo.repository.dao.interfaces.ProductDao;
import com.example.demo.repository.entities.Product;

import java.util.*;

@Service
public class ProductService {
       
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProduct(){
        return productDao.findAll();
    }


    public Optional <Product> getByID(int id){
        return productDao.findById(id);
    }

    public ProductResponse insertproduct(Product product){
        ProductResponse response = new ProductResponse();
        List<Product> products = new ArrayList<>();
        products = getAllProduct();
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()){
            Product productItem = iterator.next();
            if(productItem.getName().toLowerCase().equals(product.getName().toLowerCase())){
                response.setMessage("Product id already exist");
                return response;
            }
        }
        

        productDao.save(product);
        response.setMessage("Product added successful");
        
        return response;

    }
    

}
