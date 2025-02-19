package com.example.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Cart extends  BaseModel{
    @Column(nullable = false, unique = true)
    private Long userId;
    @Column(nullable = false)
    private Double totalAmount = 0d;
    @Column(nullable = false)
    private Integer totalItems = 0;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;
}
