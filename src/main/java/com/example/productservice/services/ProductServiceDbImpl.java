package com.example.productservice.services;

import com.example.productservice.dtos.SortDirection;
import com.example.productservice.exceptions.*;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.indices.OpenSearchProductRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class ProductServiceDbImpl implements ProductService{

    private ProductRepository productRepository;


    private CategoryRepository categoryRepository;

    private OpenSearchProductRepository openSearchProductRepository;

    public ProductServiceDbImpl(ProductRepository productRepository,
                                CategoryRepository categoryRepository,
                                OpenSearchProductRepository openSearchProductRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.openSearchProductRepository = openSearchProductRepository;
    }

    @Override
    public Product create(Product product) {
        productRepository.findBySkuAndIsDeletedIsFalse(product.getSku()).ifPresent(p -> {
            throw new ProductAlreadyExistsException("Product already exists.");
        });
        Category savedCategory = getProductCategory(product.getCategory());
        try{
            product.setCategory(savedCategory);
            Product savedProduct =  productRepository.save(product);
            openSearchProductRepository.save(savedProduct);
            return savedProduct;
        }catch (Exception e){
            throw new ProductCreationFailedException("Product creation failed. "+e.getMessage());
        }
    }
    @Override
    public Page<Product> getAll(int page, int size, String sortBy, SortDirection direction) {
        try{
            Sort sort = Sort.by(sortBy);
            if(direction.equals(SortDirection.DESC)) sort = sort.descending();
            else sort = sort.ascending();

            PageRequest pageRequest= PageRequest.of(page,size,sort);
            return productRepository.findAllByIsDeletedFalse(pageRequest);
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
    public Product update(Long id, Product product){
       Product oldProduct = productRepository.findById(id)
               .orElseThrow(() ->
                new ProductNotFoundException("Required product not found.")
        );
        if(product.getCategory() != null){
            Category category = getProductCategory(product.getCategory());
            oldProduct.setCategory(category);
        }
        if(product.getTitle() != null) {
            productRepository.findByIsDeletedIsFalseAndSkuAndIdIsNot(product.getSku(),id)
                    .ifPresent(p -> {
                        throw new ProductAlreadyExistsException("Product already exists with the same title.");
                    });
            oldProduct.setTitle(product.getTitle());
            oldProduct.setSku(product.getSku());
        }
        if(product.getPrice() != null) oldProduct.setPrice(product.getPrice());
        if(product.getDescription() != null) oldProduct.setDescription(product.getDescription());
        if(product.getImg() != null) oldProduct.setImg(product.getImg());
        if(product.getQuantity() != null) oldProduct.setQuantity(product.getQuantity());
        try{

            Product savedProduct =  productRepository.save(oldProduct);
            openSearchProductRepository.deleteById(savedProduct.getId());
            openSearchProductRepository.save(savedProduct);
            return savedProduct;
        }catch (Exception e){
            throw new ProductUpdateFaildException("Product update failed."+e.getMessage());
        }
    }

    @Override
    public Product replace(Long id, Product product){
        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Required product not found.")
                );

        productRepository.findByIsDeletedIsFalseAndSkuAndIdIsNot(product.getSku(),id)
                .ifPresent(p -> {
                    throw new ProductAlreadyExistsException("Product already exists.");
                });
        Category savedCategory = getProductCategory(product.getCategory());

        oldProduct.setTitle(product.getTitle());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCategory(savedCategory);
        oldProduct.setDescription(product.getDescription());
        oldProduct.setQuantity(product.getQuantity());
        oldProduct.setImg(product.getImg());
        try{
            Product savedProduct = productRepository.save(oldProduct);
            openSearchProductRepository.deleteById(savedProduct.getId());
            openSearchProductRepository.save(savedProduct);
            return savedProduct;
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
            openSearchProductRepository.deleteById(id);
        }catch (Exception e){
            throw new ProductDeleteFailedException("Product deletion failed."+e.getMessage());
        }
    }
    public Category getProductCategory(Category category){
        try{
            return categoryRepository.findBySku(category.getSku())
                    .orElseGet(() -> categoryRepository.save(category));
        }catch (Exception e){
            throw new ProductCategoryCreationFailedException("Product category creation failed."+e.getMessage());
        }
    }
}
