package org.workintech.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.workintech.dto.user.OrderRequest;
import org.workintech.dto.user.OrderResponse;
import org.workintech.service.user.OrderService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    @PostMapping("/{token}")
    private String saveOrder(@PathVariable String token, @RequestBody OrderRequest orderRequest){
        return orderService.saveOrder(token,orderRequest);
    }

    @GetMapping("/{token}")
    private List<OrderResponse> getAllOrder(@PathVariable String token){
        return orderService.getAllOrder(token);
    }
}
