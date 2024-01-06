package org.workintech.service;

import org.workintech.dto.CategoryResponse;
import org.workintech.entity.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    CategoryResponse save(Category category);
    CategoryResponse getById(Long  id);
    CategoryResponse update(Long id,Category category);
    CategoryResponse delete(Long id);
    void saveAll(List<Category> categories);
    List<CategoryResponse> getAll();
}
