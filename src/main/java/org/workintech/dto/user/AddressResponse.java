package org.workintech.dto.user;

public record AddressResponse(Long id, String addressTitle, String name, String surname , String phone, String city, String district, String neighborhood, String addressDetail) {
}
