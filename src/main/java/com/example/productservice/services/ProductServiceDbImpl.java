package com.example.productservice.services;

import com.example.productservice.exceptions.*;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductServiceDbImpl implements ProductService{

    private ProductRepository productRepository;


    private CategoryRepository categoryRepository;

    public ProductServiceDbImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product create(Product product) {
        Category savedCategory = getProductCategory(product.getCategory());
        try{
            product.setCategory(savedCategory);
            return productRepository.save(product);
        }catch (Exception e){
            throw new ProductCreationFailedException("Product creation failed."+e.getMessage());
        }
    }

    @Override
    public List<Product> getAll() {
        try{
            return productRepository.findAllByIsDeletedFalse();
        }catch (Exception e){
            throw new FetchAllProductsException("Failed to fetch all products."+e.getMessage());
        }
    }

    @Override
    public Product getById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) throw new ProductNotFoundException("Product not found.");
        return product.get();
    }

    @Override
    public Product update(Long id, Product product) throws ProductNotFoundException {
       Optional<Product> ifProduct = productRepository.findById(id);
        if(ifProduct.isEmpty()) throw new ProductNotFoundException("Required product not found.");
        Product oldProduct = ifProduct.get();
        if(product.getCategory() != null){
            Category category = getProductCategory(product.getCategory());
            oldProduct.setCategory(category);
        }
        if(product.getTitle() != null) oldProduct.setTitle(product.getTitle());
        if(product.getPrice() != null) oldProduct.setPrice(product.getPrice());
        if(product.getDescription() != null) oldProduct.setDescription(product.getDescription());
        if(product.getImg() != null) oldProduct.setImg(product.getImg());
        if(product.getQuantity() != null) oldProduct.setQuantity(product.getQuantity());
        try{
            return productRepository.save(oldProduct);
        }catch (Exception e){
            throw new ProductUpdateFaildException("Product update failed."+e.getMessage());
        }
    }

    @Override
    public Product replace(Long id, Product product) throws ProductNotFoundException{
        Optional<Product> ifProduct = productRepository.findById(id);
        if(ifProduct.isEmpty()) throw new ProductNotFoundException("Required product not found.");
        Category savedCategory = getProductCategory(product.getCategory());
        Product oldProduct = ifProduct.get();
        oldProduct.setTitle(product.getTitle());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCategory(savedCategory);
        oldProduct.setDescription(product.getDescription());
        oldProduct.setQuantity(product.getQuantity());
        oldProduct.setImg(product.getImg());
        try{
            return productRepository.save(oldProduct);
        }catch (Exception e){
            throw new ProductReplaceFaildException("Product update failed."+e.getMessage());
        }
    }
    @Override
    public void delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) throw new ProductNotFoundException("Product not found.");
        Product product = optionalProduct.get();
        product.setIsDeleted(true);
        try{
            productRepository.save(product);
        }catch (Exception e){
            throw new ProductDeleteFailedException("Product deletion failed."+e.getMessage());
        }
    }
    public Category getProductCategory(Category category){
        try{
            return categoryRepository.findByName(category.getName()).orElseGet(() -> categoryRepository.save(category));
        }catch (Exception e){
            throw new ProductCategoryCreationFailedException("Product category creation failed."+e.getMessage());
        }
    }
}
