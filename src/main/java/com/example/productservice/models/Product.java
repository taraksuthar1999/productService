package com.example.productservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(indexName = "products")
public class Product extends BaseModel{
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false, unique = true)
    private String sku;
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Long quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Category category;
    @Column(nullable = false)
    private String img;
}
