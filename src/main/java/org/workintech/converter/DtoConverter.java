package org.workintech.converter;

import org.workintech.dto.CategoryResponse;
import org.workintech.dto.ImagesObjResponse;
import org.workintech.dto.ProductResponse;
import org.workintech.dto.StoreResponse;
import org.workintech.dto.user.*;
import org.workintech.entity.Category;
import org.workintech.entity.ImagesObj;
import org.workintech.entity.Product;
import org.workintech.entity.Store;
import org.workintech.entity.user.*;

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
                DtoConverter.convertToImagesObjResponseList(product.getImages()),
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
                DtoConverter.convertToImagesObjResponseList(product.getImages()),
                product.getPrice(),
                product.getRating(),
                product.getStock(),
                product.getSellCount(),
                product.getCategory().getId(),
                product.getStore().getId()
        )));
        return responses;
    }

    public static StoreResponse convertToStoreResponse(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getPhone(),
                store.getTaxNumber(),
                store.getIban()
        );
    }

    public static List<StoreResponse> convertToStoreResponseList(List<Store> stores) {
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

    public static List<ImagesObjResponse> convertToImagesObjResponseList(List<ImagesObj> imagesObjs) {
        List<ImagesObjResponse> responses = new ArrayList<>();
        imagesObjs.forEach(imagesObj -> responses.add(new ImagesObjResponse(
                imagesObj.getUrl(), imagesObj.getIndex()
        )));
        return responses;
    }

    public static ImagesObjResponse convertToImagesObjResponse(ImagesObj imagesObj) {
        return new ImagesObjResponse(imagesObj.getUrl(), imagesObj.getIndex());
    }

    public static RegisterUserResponse convertToRegisterUserResponse(String message) {
        return new RegisterUserResponse(message);
    }

    public static LoginUserResponse convertToLoginUserResponse(User user) {
        return new LoginUserResponse(user.getEmail(), user.getName(), user.getRoles().stream().findFirst().orElseThrow().getId().toString(), user.getToken());
    }

    public static List<RoleResponse> convertToRoleResponseList(List<Role> roles) {
        List<RoleResponse> responses = new ArrayList<>();
        roles.forEach(role -> responses.add(new RoleResponse(
                role.getId(), role.getName(), role.getCode()
        )));
        return responses;
    }

    public static AddressResponse convertToAddressResponse(Address address) {
        return new AddressResponse(
                address.getAddressTitle(),
                address.getName(),
                address.getSurname(),
                address.getPhone(),
                address.getCity(),
                address.getDistrict(),
                address.getNeighborhood(),
                address.getAddressDetail()
        );
    }

    public static List<AddressResponse> convertToAddressResponseList(List<Address> addresses) {
        List<AddressResponse> responses = new ArrayList<>();
        addresses.forEach(address -> responses.add(new AddressResponse(
                address.getAddressTitle(),
                address.getName(),
                address.getSurname(),
                address.getPhone(),
                address.getCity(),
                address.getDistrict(),
                address.getNeighborhood(),
                address.getAddressDetail()
        )));
        return responses;
    }

    public static PaymentResponse convertToPaymentResponse(Payment payment){
        return new PaymentResponse(payment.getNumber(), payment.getName(), payment.getExpiryMonth(), payment.getExpiryYear(), payment.getCvc());
    }

    public static List<PaymentResponse> convertToPaymentResponseList(List<Payment> payments){
        List<PaymentResponse> responses = new ArrayList<>();
        payments.forEach(payment -> responses.add(new PaymentResponse(
                payment.getNumber(),
                payment.getName(),
                payment.getExpiryMonth(),
                payment.getExpiryYear(),
                payment.getCvc()
        )));
        return responses;
    }

    public static List<CartItemResponse> convertToCartItemResponseList(List<CartItem> cartItems){
        List<CartItemResponse> responses = new ArrayList<>();
        cartItems.forEach(cartItem -> responses.add(new CartItemResponse(
                cartItem.getId(),
                DtoConverter.convertToProductResponse(cartItem.getProduct()),
                cartItem.getQuantity(),
                cartItem.getIsChecked()
        )));
        return responses;
    }

    public static  CartItemResponse convertToCartItemResponse(CartItem cartItem){
        return new CartItemResponse(
                cartItem.getId(),
                DtoConverter.convertToProductResponse(cartItem.getProduct()),
                cartItem.getQuantity(),
                cartItem.getIsChecked());
    }

}

