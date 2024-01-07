package org.workintech.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.workintech.dto.ProductResponse;
import org.workintech.entity.ImagesObj;
import org.workintech.entity.Product;
import org.workintech.entity.Store;
import org.workintech.service.CategoryService;
import org.workintech.service.ImagesObjService;
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
        //System.out.println("Responses: "+responses);
        //System.out.println("Responses get body: "+responses.getBody());
        //System.out.println("Get Body Products list"+responses.getBody().get("products"));

        for(JsonNode node : Objects.requireNonNull(responses.getBody().get("products"))){
            Product product = new Product();
            Long id = node.get("id") != null ? node.get("id").asLong() : null;
            String name = node.get("name") != null ? node.get("name").asText() : null;
            String description = node.get("description") != null ? node.get("description").asText() : null;
            Double price = node.get("price") != null ? node.get("price").asDouble() : null ;
            Double rating = node.get("rating") != null ? node.get("rating").asDouble() : null;
            Integer sellCount = node.get("sell_count") != null ? node.get("sell_count").asInt() : null;
            Integer stock = node.get("stock") != null ? node.get("stock").asInt() : null;
            Long storeId = node.get("store_id") != null ? node.get("store_id").asLong() : null;
            Long categoryId = node.get("category_id") != null ? node.get("category_id").asLong() : null;
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
    @PostMapping("/")
    public ProductResponse save(@RequestBody Product product){
        return productService.save(product);
    }
    @GetMapping("/")
    public List<ProductResponse> getAll(){
        return productService.getAll();
    }
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id){
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody Product product){
        return productService.update(id,product);
    }

    @DeleteMapping("/{id}")
    public ProductResponse delete(@PathVariable Long id){
        return productService.delete(id);
    }
}
