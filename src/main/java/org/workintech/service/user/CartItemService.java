package org.workintech.service.user;

import org.workintech.dto.user.CartItemResponse;
import org.workintech.entity.user.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItemResponse> getUserCartItems(String token);
    CartItem saveToCart(String token, Long productId, int quantity, Boolean isChecked);
    void updateQuantity(Long id, boolean isAdding);
    void updateIsChecked(Long id, boolean isChecked);
    void removeFromCart(String userToken, Long cartItemId);
}
