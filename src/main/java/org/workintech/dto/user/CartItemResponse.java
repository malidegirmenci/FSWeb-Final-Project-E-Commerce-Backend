package org.workintech.dto.user;

import org.workintech.dto.ProductResponse;

public record CartItemResponse(Long id,ProductResponse product, int quantity, boolean isChecked) {
}
