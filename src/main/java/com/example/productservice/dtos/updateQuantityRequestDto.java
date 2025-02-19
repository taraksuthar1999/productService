package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class updateQuantityRequestDto {
    private Long productId;
    private Integer quantity;
}
