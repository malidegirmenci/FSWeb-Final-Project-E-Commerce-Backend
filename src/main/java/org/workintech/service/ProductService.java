package org.workintech.service;

import org.springframework.data.domain.Pageable;
import org.workintech.dto.ProductResponse;
import org.workintech.entity.Product;

import java.util.List;

public interface ProductService {
    Product finById(Long id);

    ProductResponse save(Product product);

    ProductResponse getById(Long id);

    ProductResponse update(Long id, Product product);

    ProductResponse delete(Long id);

    List<Product> saveAll(List<Product> products);

    List<ProductResponse> getAllWithParams(Integer category, String filter, String sort, Pageable pageable);

    List<ProductResponse> countAllWithParams(Integer category, String filter, String sort);

}
