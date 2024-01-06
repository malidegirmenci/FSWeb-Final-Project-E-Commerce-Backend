package org.workintech.converter;

import org.workintech.dto.CategoryResponse;
import org.workintech.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {
    public static CategoryResponse convertToCategoryResponse(Category category){
        return new CategoryResponse(
                category.getId(),
                category.getTitle(),
                category.getGender(),
                category.getCode(),
                category.getRating(),
                category.getImg());
    }
    public static List<CategoryResponse> convertToCategoryResponseList(List<Category> categories){
        List<CategoryResponse> responses = new ArrayList<>();
        categories.forEach(category -> responses.add(new CategoryResponse(
                category.getId(),
                category.getTitle(),
                category.getGender(),
                category.getCode(),
                category.getRating(),
                category.getImg()
        )));
        return responses;
    }
}
