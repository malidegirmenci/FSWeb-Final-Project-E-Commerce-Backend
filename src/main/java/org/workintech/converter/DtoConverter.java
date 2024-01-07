package org.workintech.converter;

import org.workintech.dto.CategoryResponse;
import org.workintech.dto.ProductResponse;
import org.workintech.dto.StoreResponse;
import org.workintech.entity.Category;
import org.workintech.entity.Product;
import org.workintech.entity.Store;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {
    public static CategoryResponse convertToCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getTitle(),
                category.getGender(),
                category.getCode(),
                category.getRating(),
                category.getImg());
    }

    public static List<CategoryResponse> convertToCategoryResponseList(List<Category> categories) {
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

    public static ProductResponse convertToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImages(),
                product.getPrice(),
                product.getRating(),
                product.getStock(),
                product.getSellCount(),
                product.getCategory().getId(),
                product.getStore().getId()
        );
    }

    public static List<ProductResponse> convertToProductResponseList(List<Product> products) {
        List<ProductResponse> responses = new ArrayList<>();
        products.forEach(product -> responses.add(new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImages(),
                product.getPrice(),
                product.getRating(),
                product.getStock(),
                product.getSellCount(),
                product.getCategory().getId(),
                product.getStore().getId()
        )));
        return responses;
    }
    public static StoreResponse convertToStoreResponse(Store store){
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getPhone(),
                store.getTaxNumber(),
                store.getIban()
        );
    }

    public static List<StoreResponse> convertToStoreResponseList(List<Store> stores){
        List<StoreResponse> responses = new ArrayList<>();
        stores.forEach(store -> responses.add(new StoreResponse(
                store.getId(),
                store.getName(),
                store.getPhone(),
                store.getTaxNumber(),
                store.getIban()
        )));
        return responses;
    }
}
