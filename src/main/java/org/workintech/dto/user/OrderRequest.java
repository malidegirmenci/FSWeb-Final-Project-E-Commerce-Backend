package org.workintech.dto.user;

import java.util.List;

public record OrderRequest(Long addressId, Long paymentId, Double price, List<CartItemResponse> cartItemResponse) {
}
