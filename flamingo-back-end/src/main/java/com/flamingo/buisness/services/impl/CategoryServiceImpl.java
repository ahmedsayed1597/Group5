package com.flamingo.buisness.services.impl;

import com.flamingo.persistence.entities.Product;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.flamingo.buisness.exception.AlreadyExist;
import com.flamingo.buisness.exception.notFoundException;
import com.flamingo.buisness.services.interfaces.CategoryService;
import com.flamingo.buisness.services.interfaces.ProductService;
import com.flamingo.persistence.dao.CategoryRepository;
import com.flamingo.persistence.entities.Category;
import com.flamingo.presentation.dto.CategoryDto;
import com.flamingo.presentation.responseviewmodel.CategoryResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
     private final ProductService productService;


//     @PostConstruct
//     public void DB(){
//         List<Category>categories= IntStream.rangeClosed(1,100).mapToObj(i->{Category c= new Category();c.setCategoryName("name"+i); return c;})
//
//                                                    .collect(Collectors.toList());
//         categoryRepository.saveAll(categories);
//     }
    @Override
    public CategoryDto createCategory(Category category) {

        Category checkedCategory = getCategoryByName(category.getCategoryName());
        if (checkedCategory == null) {
       return modelMapper.map(categoryRepository.save(category),CategoryDto.class);
        }
        throw new AlreadyExist("the category u are trying to add already exists" + category.getCategoryName());
    }

    @Override
    public CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String field, String orderBy) {
        Sort sorting;
        if(orderBy.equalsIgnoreCase("asc")) {
            sorting = Sort.by(field).ascending();
        }else
        sorting=Sort.by(field).descending();


        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sorting);

        Page<Category> categoriesPage = categoryRepository.findAll(pageDetails);
        List<Category> categories = categoriesPage.getContent();

        if (categories.size() == 0) {
            throw new notFoundException("No categories created till now");
        }

        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setData(categoryDtos);
        categoryResponse.setPageNumber(categoriesPage.getNumber());
        categoryResponse.setPageSize(categoriesPage.getSize());
        categoryResponse.setTotalElements(categoriesPage.getTotalElements());
        categoryResponse.setTotalPages(categoriesPage.getTotalPages());
        categoryResponse.setLastPage(categoriesPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDto updateCategory(Category category, Long categoryId) {

        Category categoryToSave=categoryRepository.findById(categoryId)
                                    .orElseThrow(()->new notFoundException("not such category exists" + category.getCategoryName()));
        category.setCategoryId(categoryId);
        return modelMapper.map( categoryRepository.save(category),CategoryDto.class);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category categoryTodelete=categoryRepository.findById(categoryId)
                .orElseThrow(()->new notFoundException("not such category exists" ));

        List<Product> products = categoryTodelete.getProducts();

        for(Product product : products){
            productService.deleteProduct(product.getProductId());

        }



        categoryRepository.delete(categoryTodelete);
        return "succeful delete ! ";
    }
    

    public Category getCategoryByName(String categoryName){
        Category category = categoryRepository.getCategoryByCategoryName(categoryName);

        return category;
    }
}
