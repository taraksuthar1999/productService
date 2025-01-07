package com.example.productservice.dtos.fakestore;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreGetProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setImg(this.image);
        Category category1 = new Category();
        category1.setName(this.category);
        product.setCategory(category1);
        product.setPrice(this.price);
        product.setDescription(this.description);
        return product;
    }
}
