package org.workintech.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.user.CartItemResponse;
import org.workintech.entity.Product;
import org.workintech.entity.user.CartItem;
import org.workintech.entity.user.User;
import org.workintech.exceptions.EcommerceException;
import org.workintech.repository.ProductRepository;
import org.workintech.repository.user.CartItemRepository;
import org.workintech.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<CartItemResponse> getUserCartItems(String token) {
        Optional<User> user = userRepository.findUserByToken(token);
        List<CartItem> cartItems = cartItemRepository.findByUserId(user.orElseThrow().getId());
        return DtoConverter.convertToCartItemResponseList(cartItems);
    }

    public CartItem saveToCart(String token, Long productId, int quantity, Boolean isChecked) {
        User user = userRepository.findUserByToken(token).orElseThrow(() -> new EcommerceException("The user with given token could not find!", HttpStatus.UNAUTHORIZED));
        Product product = productRepository.findById(productId).orElseThrow(() -> new EcommerceException("The product with given id could not find", HttpStatus.NOT_FOUND));
        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductId(user.getId(), product.getId());
        CartItem cartItem;
        if (existingCartItem.isPresent()) {
            cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }
        cartItem.setIsChecked(isChecked);
        return cartItemRepository.save(cartItem);
    }

    public void updateQuantity(Long id, boolean isAdding) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new EcommerceException("The cart item with given id could not find!", HttpStatus.NOT_FOUND));
        if (isAdding) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        }
        cartItemRepository.save(cartItem);
    }

    public void updateIsChecked(Long id, boolean isChecked) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new EcommerceException("The cart item with given id could not find!", HttpStatus.NOT_FOUND));
        cartItem.setIsChecked(isChecked);
        cartItemRepository.save(cartItem);
    }

    public void removeFromCart(String userToken, Long cartItemId) {
        userRepository.findUserByToken(userToken).orElseThrow(() -> new EcommerceException("The user with given token could not find!", HttpStatus.UNAUTHORIZED));
        cartItemRepository.deleteById(cartItemId);
    }
}
