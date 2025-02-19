package com.example.productservice.dtos.product;

import com.example.productservice.dtos.category.CategoryDto;
import com.example.productservice.models.Product;
import com.example.productservice.utils.Common;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartialProductRequestDto {

    private String title;
    private String description;
    private Double price;
    private CategoryDto category;
    private String img;
    private Long quantity;

    public Product toProduct(){
        return Product.builder()
                .title(this.title)
                .sku(Common.convertToSku(this.title))
                .img(this.img)
                .category(this.category.toCategory())
                .price(this.price)
                .description(this.description)
                .quantity(this.quantity)
                .build();
    }
}
