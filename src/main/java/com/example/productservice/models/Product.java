package com.example.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

@Entity
@Getter
@Setter
public class Product extends BaseModel{
    @Column(nullable = false, unique = true)
    private String title;
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Long quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Category category;
    @Column(nullable = false)
    private String img;
}
