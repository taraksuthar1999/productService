package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDto {
 private Integer pageNumber;
 private Integer pageSize;
 private Long total;
}
