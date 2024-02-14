package org.workintech.service.user;

import org.workintech.dto.user.OrderRequest;
import org.workintech.dto.user.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse saveOrder(String token,OrderRequest orderRequest);
    List<OrderResponse> getAllOrder(String token);
}
