package org.workintech.dto.user;

public record CartItemRequest(Long productId, int quantity, boolean isChecked) {
}
