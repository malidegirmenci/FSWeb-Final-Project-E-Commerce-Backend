package org.workintech.dto.user;

import java.util.List;

public record OrderResponse(Long id, AddressResponse address, double price, String date, List<CartItemResponse> cart) {
}
