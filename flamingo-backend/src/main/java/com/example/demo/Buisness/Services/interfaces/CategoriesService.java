package com.example.demo.Buisness.Services.interfaces;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.Dao.interfaces.CategoryDao;
import com.example.demo.Repository.Entities.Category;

@Service
public class CategoriesService {
    
    @Autowired
    private CategoryDao categoryDao;
    
    public  List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        categories = categoryDao.findAll();
        return categories;
    }
}
