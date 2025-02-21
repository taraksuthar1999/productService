package com.example.productservice.services;

import com.example.productservice.dtos.SortDirection;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceDbImpl implements SearchService{

    ProductRepository productRepository;

    public SearchServiceDbImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> searchProducts(String query,
                                        int pageNumber,
                                        int pageSize,
                                        String sortBy,
                                        SortDirection direction) {
        Sort sort = Sort.by(sortBy);
        if(direction.equals(SortDirection.DESC)) sort = sort.descending();
        else sort = sort.ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        return productRepository.findAllByIsDeletedFalseAndTitleContainingIgnoreCase(query,pageable);
    }
}
