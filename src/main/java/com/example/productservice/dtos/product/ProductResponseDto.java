package com.example.productservice.dtos.product;

import com.example.productservice.dtos.category.CategoryDto;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private CategoryDto category;
    private String img;
    private Long quantity;

    public static ProductResponseDto fromProduct(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImg(product.getImg());
        productResponseDto.setCategory(CategoryDto.fromCategory(product.getCategory()));
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setQuantity(product.getQuantity());
        return productResponseDto;
    }

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(this.title);
        product.setImg(this.img);
        product.setCategory(this.category.toCategory());
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setQuantity(this.quantity);
        return product;
    }
}
