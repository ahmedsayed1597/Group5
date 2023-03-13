package com.flamingo.buisness.services.impl;

import com.flamingo.buisness.exception.AlreadyExist;
import com.flamingo.buisness.exception.notFoundException;
import com.flamingo.buisness.services.interfaces.FileService;
import com.flamingo.buisness.services.interfaces.ProductService;
import com.flamingo.persistence.dao.CategoryRepository;
import com.flamingo.persistence.dao.ProductRepository;
import com.flamingo.persistence.entities.Category;
import com.flamingo.persistence.entities.Product;
import com.flamingo.presentation.dto.ProductDTO;
import com.flamingo.presentation.responseviewmodel.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    private final FileService fileService;

//    private final CartRepo cartRepo;
//
//    private final CartService cartService;

    @Value("${project.image}")
    private String path;

//    public void DB(){
//        Category c = categoryRepository.findById(1l).get();
//        List<Product>products= IntStream.rangeClosed(1,100).mapToObj(i->{Product p= new Product();p.setProductName("name"+i);p.setCategory(c);p.setPrice(100.5); return p;})
//                .collect(Collectors.toList());
//        productRepository.saveAll(products);
//    }
    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {


        Category category= categoryRepository.findById(categoryId).orElseThrow(()->new notFoundException("no such category exists ! "));
        List<Product> products = category.getProducts();

        boolean isProductExist = false;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(product.getProductName())
                    && products.get(i).getDescription().equals(product.getDescription())) {

                isProductExist = true;
                break;
            }
        }

        if (!isProductExist) {
            if (product.getImage()==null) {
                product.setImage("default.png");
            }

            product.setCategory(category);

            return modelMapper.map(productRepository.save(product), ProductDTO.class);
        } else {
            throw new AlreadyExist("Product already exists !!!");
        }

    }

    @Override
    public ProductDTO addProductWithImage(Long categoryId
                                        ,  String productName,
                                          String description,
                                          int quantity,
                                          double price,
                                          MultipartFile image) throws IOException{


    Category category= categoryRepository.findById(categoryId).orElseThrow(()->new notFoundException("no such category exists ! "));
        List<Product> products = category.getProducts();
        boolean isProductExist = false;
        Product product = new Product(productName, description, quantity, price);


        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(product.getProductName())
                    && products.get(i).getDescription().equals(product.getDescription())) {

                isProductExist = true;
                break;
            }
        }




        if (!isProductExist) {
            String fileName = fileService.uploadImage(path, image);
            if (fileName != null) {
                product.setImage(fileName);
            }
            if (product.getImage()==null) {
                product.setImage("default.png");
            }

            product.setCategory(category);

            return modelMapper.map(productRepository.save(product), ProductDTO.class);
        } else {
            throw new AlreadyExist("Product already exists !!!");
        }

    }

    @Override
    public ProductDTO addProductWithImageWithJson(Long categoryId
                                         , Product product, MultipartFile image) throws IOException{


        Category category= categoryRepository.findById(categoryId).orElseThrow(()->new notFoundException("no such category exists ! "));
        List<Product> products = category.getProducts();
        boolean isProductExist = false;


        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(product.getProductName())
                    && products.get(i).getDescription().equals(product.getDescription())) {

                isProductExist = true;
                break;
            }
        }




        if (!isProductExist) {
            String fileName = fileService.uploadImage(path, image);
            if (fileName != null) {
                product.setImage(fileName);
            }
            if (product.getImage()==null) {
                product.setImage("default.png");
            }

            product.setCategory(category);

            return modelMapper.map(productRepository.save(product), ProductDTO.class);
        } else {
            throw new AlreadyExist("Product already exists !!!");
        }

    }
    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String field, String orderBy) {

        //define sorting will be by which field and order
        Sort sorting ;
        if (orderBy.equalsIgnoreCase("asc")) {
            sorting = Sort.by(field).ascending();
        }else
            sorting=Sort.by(field).descending();

        //creating the page details by setting the page number and page size and sorting mechanism
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sorting);


        //creating page of products
        Page<Product> productsPage = productRepository.findAll(pageDetails);
        //creating products list from repo
        List<Product> products =   productsPage.getContent();


        //maping products to productsd Dtos
        List<ProductDTO> productsDto = new ArrayList<>();

        for(Product mappedProduct:products){
            productsDto.add(modelMapper.map(mappedProduct,ProductDTO.class));
        }
        //creating product response object

        return new ProductResponse(productsDto,productsPage.getNumber(),productsPage.getSize(),productsPage.getTotalElements()
                                                        ,productsPage.getTotalPages(),productsPage.isLast());
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String field, String orderBy) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new notFoundException("no such category Exists ! "));

        Sort sorting ;

        if (orderBy.equalsIgnoreCase("asc")) {
            sorting = Sort.by(field).ascending();
        }else
            sorting=Sort.by(field).descending();

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sorting);

        Page<Product> productsPage=productRepository.findProductsByCategoryId(category.getCategoryId(),pageDetails);

        List<Product>products = productsPage.getContent();


        List<ProductDTO>productDtos = products.stream()
                                        .map(p->modelMapper.map(p,ProductDTO.class))
                                        .collect(Collectors.toList());

        return new ProductResponse(productDtos,productsPage.getNumber(),productsPage.getSize()
                                    ,productsPage.getTotalElements(),productsPage.getTotalPages(),productsPage.isLast());
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sorting = (sortOrder.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sorting);

        Page<Product> productPage = productRepository.findProductByProductNameLike(keyword,pageDetails);

        List<Product>products=productPage.getContent();

        if (products == null)
            throw new notFoundException("no products found for this keyword ! ");

        List<ProductDTO> productDtos = products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());


        return new ProductResponse(productDtos,productPage.getNumber(),productPage.getSize()
                    ,productPage.getTotalElements(),productPage.getTotalPages(),productPage.isLast());
    }

    @Override
    public ProductDTO updateProduct(Long productId, Product product) {

        Product updatedProduct = productRepository.findById(productId).orElseThrow(()->new notFoundException("no such product Exist ! "));

        product.setProductId(productId);
        product.setImage(updatedProduct.getImage());
        product.setCategory(updatedProduct.getCategory());

//        List<Cart> carts = cartRepo.findCartsByProductId(productId);
//
//        List<CartDTO> cartDTOs = carts.stream().map(cart -> {
//            CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
//
//            List<ProductDTO> products = cart.getCartItems().stream()
//                    .map(p -> modelMapper.map(p.getProduct(), ProductDTO.class)).collect(Collectors.toList());
//
//            cartDTO.setProducts(products);
//
//            return cartDTO;
//
//        }).collect(Collectors.toList());
//
//        cartDTOs.forEach(cart -> cartService.updateProductInCarts(cart.getCartId(), productId));

        return modelMapper.map(productRepository.save(product),ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        Product productFromDB = productRepository.findById(productId).orElseThrow(()->new notFoundException("no such product Exist ! "));
        String fileName = fileService.uploadImage(path, image);
        productFromDB.setImage(fileName);
        return modelMapper.map(productRepository.save(productFromDB), ProductDTO.class);
    }

    @Override
    public String deleteProduct(Long productId) {
        Product productFromDB = productRepository.findById(productId).orElseThrow(()->new notFoundException("no such product Exist ! "));

//        List<Cart> carts = cartRepo.findCartsByProductId(productId);
//
//        carts.forEach(cart -> cartService.deleteProductFromCart(cart.getCartId(), productId));

        productRepository.delete(productFromDB);

        return "produt is succefuly deleted ! ";
    }


    public byte[] downloadImages(Long productId) throws IOException {
        Product productFromDB = productRepository.findById(productId).orElseThrow(()->new notFoundException("no such product Exist ! "));
        return fileService.downloadImage(productFromDB.getImage());

    }
}
