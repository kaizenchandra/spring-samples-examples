package com.example.demo.controller;


import com.example.demo.api.model.APIResponse;
import com.example.demo.api.model.request.ProductRequest;
import com.example.demo.api.model.response.ProductResponse;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // get all products
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<List<ProductResponse>>> getAllProducts() {
        List<ProductResponse> allProducts = productService.fetchAllProducts();
        return ResponseEntity.ok(new APIResponse<>(allProducts, "Success", HttpStatus.OK.value()));
    }

    // get product by id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<ProductResponse>> getProductById(@PathVariable Long id) {
        ProductResponse productResponse = productService.fetchProductById(id);
        return new ResponseEntity<>(new APIResponse<>(productResponse, "Success", HttpStatus.OK.value()), HttpStatus.OK);
    }
    // add product
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<ProductResponse>> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.addProduct(productRequest);
        return new ResponseEntity<>(new APIResponse<>(productResponse, "Success", HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    // update product
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<ProductResponse>> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Long id) {
        ProductResponse productResponse = productService.modifyProduct(productRequest, id);
        return  ResponseEntity.ok(new APIResponse<>(productResponse, "Success", HttpStatus.OK.value()));
    }

    // update product
    @PatchMapping(value = "/{id}/{status}")
    public ResponseEntity<APIResponse<ProductResponse>> updateStatus(@PathVariable String status, @PathVariable Long id) {
        ProductResponse productResponse = productService.modifyStatus(status, id);
        return  ResponseEntity.ok(new APIResponse<>(productResponse, "Success", HttpStatus.OK.value()));
    }
    // delete product
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.removeProductById(id);
        return ResponseEntity.noContent().build();
    }
}
