package com.example.productservice.dtos.fakestore;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductRequestDto {

    private String title;
    private String description;
    private double price;
    private String category;
    private String image;

    public static FakeStoreCreateProductRequestDto fromProduct(Product product){

        FakeStoreCreateProductRequestDto fakeStoreCreateProductDto  = new FakeStoreCreateProductRequestDto();
        fakeStoreCreateProductDto.setTitle(product.getTitle());
        fakeStoreCreateProductDto.setCategory(product.getCategory().getName());
        fakeStoreCreateProductDto.setImage(product.getImg());
        fakeStoreCreateProductDto.setDescription(product.getDescription());
        fakeStoreCreateProductDto.setPrice(product.getPrice());
        return fakeStoreCreateProductDto;

    }


}
