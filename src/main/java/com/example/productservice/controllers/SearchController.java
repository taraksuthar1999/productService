package com.example.productservice.controllers;

import com.example.productservice.dtos.ResponseDto;
import com.example.productservice.dtos.ResponseStatus;
import com.example.productservice.dtos.SortDirection;
import com.example.productservice.dtos.product.ProductResponseDto;
import com.example.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/products")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDto<Page<ProductResponseDto>>> searchProducts(@RequestParam(defaultValue = "") String query,
                                                                                @RequestParam(defaultValue = "0") Integer page,
                                                                                @RequestParam(defaultValue = "10") Integer size,
                                                                                @RequestParam(defaultValue = "id") String sortBy,
                                                                                @RequestParam(defaultValue = "ASC") SortDirection direction){
        // search products
        Page<ProductResponseDto> products = searchService.searchProducts(query, page, size, sortBy, direction)
                .map(ProductResponseDto::fromProduct);

        return ResponseEntity.ok().body(
                new ResponseDto<>(
                        ResponseStatus.SUCCESS,
                        "Search Results fetched successfully.",
                        products)
        );
    }
}
