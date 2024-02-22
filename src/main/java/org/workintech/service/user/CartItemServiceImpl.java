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
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<CartItemResponse> getUserCartItems(String token) {
        Optional<User> user = userRepository.findUserByToken(token);
        List<CartItem> cartItemsInUser = cartItemRepository.findByUserId(user.orElseThrow().getId());
        List<CartItem> activeCartItems = cartItemsInUser.stream()
                .filter(CartItem::getIsActive) // Replace with the actual getter method
                .collect(Collectors.toList());
        return DtoConverter.convertToCartItemResponseList(activeCartItems);
    }

    public CartItem saveToCart(String token, Long productId, int quantity, Boolean isChecked) {
        User user = userRepository.findUserByToken(token).orElseThrow(() -> new EcommerceException("The user with given token could not find!", HttpStatus.UNAUTHORIZED));
        Product product = productRepository.findById(productId).orElseThrow(() -> new EcommerceException("The product with given id could not find", HttpStatus.NOT_FOUND));
        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductId(user.getId(), product.getId());
        CartItem cartItem;
        if (existingCartItem.isPresent()) {
            if(existingCartItem.get().getIsActive()){
                cartItem = existingCartItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            }else{
                cartItem = existingCartItem.get();
                cartItem.setQuantity(quantity);
            }
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }
        cartItem.setIsActive(true);
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

    public void delete(String userToken, Long cartItemId) {
        User user = userRepository.findUserByToken(userToken).orElseThrow(() -> new EcommerceException("The user with given token could not find!", HttpStatus.UNAUTHORIZED));
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new EcommerceException("The cart item with given token could not find!", HttpStatus.NOT_FOUND));
        cartItemRepository.delete(cartItem);
    }
}
