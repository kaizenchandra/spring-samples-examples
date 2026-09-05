package com.example.demo.service;

import com.example.demo.api.ex.ProductNotFoundException;
import com.example.demo.api.model.request.ProductRequest;
import com.example.demo.api.model.response.ProductResponse;
import com.example.demo.persitance.model.Product;
import com.example.demo.persitance.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductItemService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> fetchAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(),
                product.getPrice(),product.getStatus()))
                .toList();
    }

    @Override
    public ProductResponse fetchProductById(Long id) {
        Product product = getProductOrElseThrow(id);
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStatus());
    }

    @Override
    public ProductResponse addProduct(ProductRequest createdProductRequest) {
        Product product = Product.builder()
                .name(createdProductRequest.name())
                .price(createdProductRequest.price())
                .build();
        Product saveProduct = productRepository.save(product);
        return new ProductResponse(saveProduct.getId(), saveProduct.getName(), saveProduct.getPrice(), saveProduct.getStatus());
    }

    @Override
    public ProductResponse modifyProduct(ProductRequest updatedProductRequest, Long id) {
        Product product = getProductOrElseThrow(id);
        product.setName(updatedProductRequest.name());
        product.setPrice(updatedProductRequest.price());
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStatus());
        // map to response
    }

    @Override
    public void removeProductById(Long id) {
        Product product = getProductOrElseThrow(id);
        productRepository.delete(product);
    }

    private Product getProductOrElseThrow(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public ProductResponse modifyStatus(String status, Long id) {
        Product product = getProductOrElseThrow(id);
        product.setStatus(status);
        productRepository.save(product);
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStatus());
    }
}
