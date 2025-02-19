package com.example.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CartItem extends BaseModel{
    @ManyToOne
    private Product product;
    private Integer quantity;
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private Cart cart;
    public CartItem(){}
}
