package org.workintech.dto;

public record ProductResponse(Long id, String name, String description,String img, Double price, Double rating, Integer stock, Integer sellCount, Long category_id) {
}
