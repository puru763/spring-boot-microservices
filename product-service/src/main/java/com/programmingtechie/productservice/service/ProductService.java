package com.programmingtechie.productservice.service;

import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.dto.ProductResponse;
import com.programmingtechie.productservice.model.Product;
import com.programmingtechie.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        try {
            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();

            productRepository.save(product);
            log.info("Product {} is saved", product.getId());
        } catch (Exception ex) {
            throw new ProductCreationException("Error creating product", ex);
        }
    }

    public List<ProductResponse> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();

            return products.stream().map(this::mapToProductResponse).toList();
        } catch (Exception ex) {
            throw new ProductRetrievalException("Error retrieving products", ex);
        }
    }

    private ProductResponse mapToProductResponse(Product product) {
        try {
            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();
        } catch (Exception ex) {
            throw new ProductMappingException("Error mapping product to response", ex);
        }
    }
}
