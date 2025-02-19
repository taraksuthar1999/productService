package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateQuantityResponseDto {
    private Long productId;
    private Integer quantity;
}
