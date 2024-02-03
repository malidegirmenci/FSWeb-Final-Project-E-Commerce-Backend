package org.workintech.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.user.CartItemRequest;
import org.workintech.dto.user.CartItemResponse;
import org.workintech.service.user.CartService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartItemController {
    private CartService cartService;
    @GetMapping("/{token}")
    public List<CartItemResponse> getUserCart(@PathVariable String token){
        return cartService.getUserCartItems(token);
    }
    @PostMapping("/{token}")
    public CartItemResponse addToCart(@PathVariable String token, @RequestBody CartItemRequest cartItemRequest){
        return DtoConverter.convertToCartItemResponse(cartService.addToCart(token, cartItemRequest.productId(), cartItemRequest.quantity(), cartItemRequest.isChecked()));
    }
    @DeleteMapping("/{token}/{cartItemId}")
    public String removeFromCart(@PathVariable String token, @PathVariable Long cartItemId){
        cartService.removeFromCart(token,cartItemId);
        return "Cart item has been removed";
    }
}
