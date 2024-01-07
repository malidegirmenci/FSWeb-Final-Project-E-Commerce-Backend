package org.workintech.dto;

import org.workintech.entity.ImagesObj;

import java.util.List;

public record ProductResponse(Long id, String name, String description, List<ImagesObj> images, Double price, Double rating, Integer stock, Integer sellCount, Long category_id, Long store_id) {
}
