package org.workintech.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workintech.entity.user.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);
}
