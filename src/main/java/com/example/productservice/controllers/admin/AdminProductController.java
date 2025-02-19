package com.example.productservice.controllers.admin;


import com.example.productservice.dtos.ResponseDto;
import com.example.productservice.dtos.ResponseStatus;
import com.example.productservice.dtos.SortDirection;
import com.example.productservice.dtos.product.*;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    private ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<ProductResponseDto>> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
            Product product = productRequestDto.toProduct();
            product =  productService.create(product);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto<>(
                            ResponseStatus.SUCCESS,
                            "Product created successfully.",
                            ProductResponseDto.fromProduct(product)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto<PartialProductResponseDto>> updateProduct(@PathVariable Long id, @RequestBody PartialProductRequestDto partialProductRequestDto) {
            Product product = partialProductRequestDto.toProduct();
            productService.update(id,product);
            PartialProductResponseDto response = PartialProductResponseDto.fromProduct(product);
            return ResponseEntity.ok()
                    .body(new ResponseDto<>(
                            ResponseStatus.SUCCESS,
                            "Product updated successfully.",
                            response));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ProductResponseDto>> replaceProduct(@PathVariable Long id,@Valid @RequestBody ProductRequestDto productRequestDto){
            Product product = productService.replace(id, productRequestDto.toProduct());
            ProductResponseDto response = ProductResponseDto.fromProduct(product);
            return ResponseEntity.ok()
                    .body(new ResponseDto<>(
                            ResponseStatus.SUCCESS,
                            "Product replaced successfully.",
                            response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Object>> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok()
                .body(new ResponseDto<>(
                        ResponseStatus.SUCCESS,
                        "Product deleted successfully.",
                        null));
    }
}
