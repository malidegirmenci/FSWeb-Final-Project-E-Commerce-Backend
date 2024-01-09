package org.workintech.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.workintech.dto.ProductResponse;
import org.workintech.dto.ProductsWithTotalResponse;
import org.workintech.entity.Product;
import org.workintech.service.CategoryService;
import org.workintech.service.ProductService;
import org.workintech.service.StoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private StoreService storeService;
    private static final String GET_ALL_PRODUCTS = "https://workintech-fe-ecommerce.onrender.com/products?limit=587";
    private RestTemplateBuilder restTemplateBuilder;

    @PostMapping("/all")
    public String saveAll() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JsonNode> responses = restTemplate.getForEntity(GET_ALL_PRODUCTS, JsonNode.class);
        List<Product> products = new ArrayList<>();

        for (JsonNode node : Objects.requireNonNull(Objects.requireNonNull(responses.getBody()).get("products"))) {
            Product product = new Product();
            Long id = node.get("id").asLong();
            String name = node.get("name").asText();
            String description = node.get("description").asText();
            Double price = node.get("price").asDouble();
            Double rating = node.get("rating").asDouble();
            Integer sellCount = node.get("sell_count").asInt();
            Integer stock = node.get("stock").asInt();
            Long storeId = node.get("store_id").asLong();
            Long categoryId = node.get("category_id").asLong();
            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setRating(rating);
            product.setSellCount(sellCount);
            product.setStock(stock);
            product.setCategory(categoryService.findById(categoryId));
            product.setStore(storeService.findById(storeId));
            products.add(product);
        }
        productService.saveAll(products);
        return "Data transfer completed successfully";
    }

    @PostMapping
    public ProductResponse save(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping
    public ProductsWithTotalResponse getAll(
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "24") Integer limit,
            @RequestParam(defaultValue = "") Integer category,
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "") String sort) {
        Pageable pageable = PageRequest.of(offset, limit);
        List<ProductResponse> products = productService.getAllWithParams(category, filter, sort, pageable);
        List<ProductResponse> unOffsetAndLimitlessProductList = productService.countAllWithParams(category, filter, sort);
        return new ProductsWithTotalResponse(products, unOffsetAndLimitlessProductList.size());
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@Positive @PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@Positive @PathVariable Long id,@Valid @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public ProductResponse delete(@Positive @PathVariable Long id) {
        return productService.delete(id);
    }


}
