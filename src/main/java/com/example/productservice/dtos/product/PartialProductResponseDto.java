package com.example.productservice.dtos.product;

import com.example.productservice.dtos.category.CategoryDto;
import com.example.productservice.models.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartialProductResponseDto {
    private String title;
    private String description;
    private Double price;
    private CategoryDto category;
    private String img;
    private Long quantity;

    public static PartialProductResponseDto fromProduct(Product product){
        PartialProductResponseDto partialProductResponseDto = new PartialProductResponseDto();
        partialProductResponseDto.setTitle(product.getTitle());
        partialProductResponseDto.setCategory(CategoryDto.fromCategory(product.getCategory()));
        partialProductResponseDto.setImg(product.getImg());
        partialProductResponseDto.setDescription(product.getDescription());
        partialProductResponseDto.setPrice(product.getPrice());
        partialProductResponseDto.setQuantity(product.getQuantity());
        return partialProductResponseDto;
    }

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(this.title);
        product.setImg(this.img);
        product.setCategory(this.category.toCategory());
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setQuantity(this.quantity);
        return product;
    }
}
