package com.example.demo.buisness.services.interfaces;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.buisness.exceptions.ProductNotFoundException;
import com.example.demo.presentation.responseViewModel.CategoryResponse;
import com.example.demo.repository.dao.interfaces.CategoryDao;
import com.example.demo.repository.entities.Category;

@Service
public class CategoriesService {
    
    @Autowired
    private CategoryDao categoryDao;
    
    public  List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        categories = categoryDao.findAll();
        return categories;
    }

    public  List<Category> getAllCategoriesPaged(int start , int size){
        if(categoryDao.findAll().size()>size)
        return categoryDao.findAll().subList(start, size);
        throw new ProductNotFoundException("out of bounds exception");
    }

    public CategoryResponse insertCategory(Category category){
        CategoryResponse response = new CategoryResponse();
        List<Category> categires = new ArrayList<>();
        categires = getAllCategories();
        Iterator iterator = categires.iterator();
        while(iterator.hasNext()){
            String categotyItem = (String) iterator.next();
            if(categotyItem.toLowerCase().equals(category.getCategoryName().toLowerCase())){
                response.setMessage("Category id already exist");
                return response;
            }
        }
        

        categoryDao.save(category);
        response.setMessage("Category added successful");
        
        return response;

    }

    public CategoryResponse delete(int id){
        CategoryResponse response = new CategoryResponse();
        List<Category> categires = new ArrayList<>();
        categires = getAllCategories();
        Iterator iterator = categires.iterator();
        while(iterator.hasNext()){
            Category categoryItem = (Category) iterator.next();
            if(categoryItem.getId() == id){
                response.setMessage("Category deleted successful");
                categoryDao.deleteById(id);
                return response;
            }
        }        
        response.setMessage("Category not found");
        return response;
    }

    public Optional <Category> getByID(int id){
        return categoryDao.findById(id);
    }

    public Category getCategoryByName(String categoryName){
        return categoryDao.getCategoryByCategoryName(categoryName);
    }
    
}
