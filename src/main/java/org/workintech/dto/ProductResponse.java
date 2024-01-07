package org.workintech.dto;

import java.util.List;

public record ProductResponse(Long id, String name, String description, List<ImagesObjResponse> images, Double price, Double rating, Integer stock, Integer sellCount, Long category_id, Long store_id) {
}
