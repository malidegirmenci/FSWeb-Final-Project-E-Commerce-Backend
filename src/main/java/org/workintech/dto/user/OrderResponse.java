package org.workintech.dto.user;

import java.util.List;

public record OrderResponse(AddressResponse addressResponse, double price, String date, List<CartItemResponse> cartItemResponses) {
}
