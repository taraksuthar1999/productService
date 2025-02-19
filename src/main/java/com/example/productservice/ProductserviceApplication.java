package com.example.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(
//        includeFilters = @ComponentScan.Filter(
//                type = FilterType.ASSIGNABLE_TYPE, classes = JpaRepository.class))
//@EnableElasticsearchRepositories(
//        includeFilters = @ComponentScan.Filter(
//                type = FilterType.ASSIGNABLE_TYPE, classes = ElasticsearchRepository.class))
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.productservice.repositories")
@EnableElasticsearchRepositories(basePackages = "com.example.productservice.indices")
public class ProductserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

}
