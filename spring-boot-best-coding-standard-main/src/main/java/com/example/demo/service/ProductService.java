package com.example.demo.service;

import com.example.demo.api.model.request.ProductRequest;
import com.example.demo.api.model.response.ProductResponse;

import java.util.List;



public interface ProductService {
    List<ProductResponse> fetchAllProducts();
    ProductResponse fetchProductById(Long id);
    ProductResponse addProduct(ProductRequest product);
    ProductResponse modifyProduct(ProductRequest product, Long id);
    void removeProductById(Long id);

    ProductResponse modifyStatus(String status, Long id);
}
