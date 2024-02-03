package org.workintech.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.workintech.entity.ImagesObj;
import org.workintech.service.ImagesObjService;
import org.workintech.service.ProductService;

import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ImagesObjController {
    private ImagesObjService imagesObjService;
    private ProductService productService;
    private static final String GET_ALL_PRODUCTS = "https://workintech-fe-ecommerce.onrender.com/products?limit=587";
    private RestTemplateBuilder restTemplateBuilder;

    @PostMapping("/all/images")
    public String saveAll() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JsonNode> responses = restTemplate.getForEntity(GET_ALL_PRODUCTS, JsonNode.class);
        for (JsonNode node : Objects.requireNonNull(Objects.requireNonNull(responses.getBody()).get("products"))) {
            JsonNode imagesNode = node.get("images");
            long productId = node.get("id").asLong();
            for (JsonNode subNode : imagesNode) {
                ImagesObj imagesObj = new ImagesObj();
                imagesObj.setUrl(subNode.get("url").asText());
                imagesObj.setIndex(subNode.get("index").asInt());
                imagesObj.setProduct(productService.finById(productId - 1));
                imagesObjService.save(imagesObj);
            }
        }
        return "All images fetched";
    }
}
