package org.workintech.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.user.CartItemRequest;
import org.workintech.dto.user.CartItemResponse;
import org.workintech.service.user.CartItemService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartItemController {
    private CartItemService cartItemService;

    @GetMapping("/{token}")
    public List<CartItemResponse> getUserCart(@PathVariable String token) {
        return cartItemService.getUserCartItems(token);
    }

    @PostMapping("/{token}")
    public CartItemResponse saveToCart(@PathVariable String token, @RequestBody CartItemRequest cartItemRequest) {
        return DtoConverter.convertToCartItemResponse(cartItemService.saveToCart(token, cartItemRequest.productId(), cartItemRequest.quantity(), cartItemRequest.isChecked()));
    }

    @PutMapping("/{id}/quantity/{isAdding}")
    public String updateQuantity(@PathVariable Long id, @PathVariable Boolean isAdding) {
        cartItemService.updateQuantity(id, isAdding);
        return "Quantity has been updated";
    }

    @PutMapping("/{id}/checkCartItem/{isChecked}")
    public String updateIsChecked(@PathVariable Long id, @PathVariable Boolean isChecked) {
        cartItemService.updateIsChecked(id, isChecked);
        return "Checked has been updated";
    }

    @DeleteMapping("/{token}/{cartItemId}")
    public String removeFromCart(@PathVariable String token, @PathVariable Long cartItemId) {
        cartItemService.delete(token, cartItemId);
        return "Cart item has been removed";
    }
}
