package com.example.productservice.dtos.product;

import com.example.productservice.dtos.category.CategoryDto;
import com.example.productservice.models.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartialProductResponseDto {
    private String title;
    private String description;
    private Double price;
    private CategoryDto category;
    private String img;

    public static PartialProductResponseDto fromProduct(Product product){
       return PartialProductResponseDto.builder()
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(CategoryDto.fromCategory(product.getCategory()))
                .img(product.getImg())
                .build();
    }
}
