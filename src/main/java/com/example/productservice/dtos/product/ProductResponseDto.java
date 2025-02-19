package com.example.productservice.dtos.product;

import com.example.productservice.dtos.category.CategoryDto;
import com.example.productservice.models.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private CategoryDto category;
    private String img;

    public static ProductResponseDto fromProduct(Product product){
        return ProductResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(CategoryDto.fromCategory(product.getCategory()))
                .img(product.getImg())
                .build();
    }

}
