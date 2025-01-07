package com.example.productservice.dtos.product;

import com.example.productservice.dtos.category.CategoryDto;
import com.example.productservice.models.Product;
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
