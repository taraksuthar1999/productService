package com.example.productservice.models;

import com.example.productservice.utils.DateTimeUtils;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isActive = true;
    @Column(updatable = false)
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Boolean isDeleted = false;

    @PrePersist
    public void prePersist(){
        this.createdAt = DateTimeUtils.getUTCDateTime(LocalDateTime.now());
        this.updatedAt = DateTimeUtils.getUTCDateTime(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = DateTimeUtils.getUTCDateTime(LocalDateTime.now());
    }
}
