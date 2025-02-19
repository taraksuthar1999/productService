package com.example.productservice.dtos.category;
import com.example.productservice.models.Category;
import com.example.productservice.utils.Common;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDto {
    private String name;

    public static CategoryDto fromCategory(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public Category toCategory(){
        Category category = new Category();
        category.setName(this.name);
        category.setSku(Common.convertToSku(this.name));
        return category;
    }
}
