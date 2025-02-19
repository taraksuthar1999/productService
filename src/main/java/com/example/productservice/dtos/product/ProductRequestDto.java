package com.example.productservice.dtos.product;

import com.example.productservice.dtos.category.CategoryDto;
import com.example.productservice.models.Product;
import com.example.productservice.utils.Common;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ProductRequestDto {
    @NotNull(message = "Title is required.")
    private String title;
    @NotNull(message = "Description is required.")
    private String description;
    @NotNull(message = "Price is required.")
    private Double price;
    @NotNull(message = "Category is required.")
    private CategoryDto category;
    @NotNull(message = "Image is required.")
    private String img;
    @NotNull(message = "Quantity is required.")
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
