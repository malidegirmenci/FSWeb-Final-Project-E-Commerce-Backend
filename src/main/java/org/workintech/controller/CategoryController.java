package org.workintech.controller;


import lombok.AllArgsConstructor;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.workintech.dto.CategoryResponse;
import org.workintech.entity.Category;
import org.workintech.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    private static final String GET_ALL_CATEGORIES = "https://workintech-fe-ecommerce.onrender.com/categories";
    private RestTemplateBuilder restTemplateBuilder;

    @PostMapping("/all")
    public String saveAll() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JsonNode> responses = restTemplate.getForEntity(GET_ALL_CATEGORIES, JsonNode.class);
        List<Category> categories = new ArrayList<>();
        for (JsonNode node : Objects.requireNonNull(responses.getBody())) {
            Long id = node.get("id").asLong();
            String title = node.get("title").asText();
            String gender = node.get("gender").asText();
            String code = node.get("code").asText();
            Double rating = node.get("rating").asDouble();
            String img = node.get("img").asText();
            Category category = new Category();
            category.setId(id);
            category.setTitle(title);
            category.setGender(gender);
            category.setCode(code);
            category.setRating(rating);
            category.setImg(img);
            categories.add(category);
        }
        categoryService.saveAll(categories);
        return "Data transfer completed successfully";
    }

    @PostMapping
    public CategoryResponse save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody Category category){
        return categoryService.update(id,category);
    }
    @DeleteMapping("/{id}")
    public CategoryResponse delete(@PathVariable Long id){
        return categoryService.delete(id);
    }

}
