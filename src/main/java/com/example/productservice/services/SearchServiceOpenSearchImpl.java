package com.example.productservice.services;

import com.example.productservice.dtos.SortDirection;
import com.example.productservice.indices.OpenSearchProductRepository;
import com.example.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SearchServiceOpenSearchImpl implements SearchService {

    OpenSearchProductRepository openSearchProductRepository;

    public SearchServiceOpenSearchImpl(OpenSearchProductRepository openSearchProductRepository) {
        this.openSearchProductRepository = openSearchProductRepository;
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
        return openSearchProductRepository.findAllByIsDeletedFalseAndTitleContainingIgnoreCase(query,pageable);
    }

}
