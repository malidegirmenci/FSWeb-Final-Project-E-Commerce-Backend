package org.workintech.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workintech.entity.user.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
}