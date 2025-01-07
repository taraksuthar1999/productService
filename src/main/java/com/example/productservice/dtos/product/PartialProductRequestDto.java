package com.example.productservice.dtos.product;

import com.example.productservice.dtos.category.CategoryDto;
import com.example.productservice.models.Product;
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

    public static ProductRequestDto fromProduct(Product product){
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setTitle(product.getTitle());
        productRequestDto.setCategory(CategoryDto.fromCategory(product.getCategory()));
        productRequestDto.setImg(product.getImg());
        productRequestDto.setDescription(product.getDescription());
        productRequestDto.setPrice(product.getPrice());
        productRequestDto.setQuantity(product.getQuantity());
        return productRequestDto;
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
