package org.workintech.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.user.OrderRequest;
import org.workintech.dto.user.OrderResponse;
import org.workintech.entity.user.*;
import org.workintech.exceptions.EcommerceException;
import org.workintech.repository.user.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private CartItemRepository cartItemRepository;
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private PaymentRepository paymentRepository;

    @Override
    public String saveOrder(String token,OrderRequest orderRequest) {
        User user = userRepository.findUserByToken(token).orElseThrow(() -> new EcommerceException("The user with given token could not find!", HttpStatus.UNAUTHORIZED));
        Address address = addressRepository.findById(orderRequest.addressId()).orElseThrow(() -> new EcommerceException("The address with given id could not find!",HttpStatus.NOT_FOUND));
        Payment payment = paymentRepository.findById(orderRequest.paymentId()).orElseThrow(() -> new EcommerceException("The payment with given id could not find!", HttpStatus.NOT_FOUND));
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setPayment(payment);

        List<CartItem> cartItems = new ArrayList<>();
        orderRequest.cartItemResponse().forEach((cartItemResponse -> {
            cartItems.add(
                    cartItemRepository.findById(cartItemResponse.id()).orElseThrow(()->new EcommerceException("The cart item with given token could not find",HttpStatus.NOT_FOUND)));
        }));
        order.setCartItems(cartItems);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        order.setDate(formattedDateTime);

        order.setPrice(orderRequest.price());

        orderRepository.save(order);
        return "Created order";
    }

    @Override
    public List<OrderResponse> getAllOrder(String token) {
       User user = userRepository.findUserByToken(token).orElseThrow(() -> new EcommerceException("The user with given id could not find!",HttpStatus.UNAUTHORIZED));
       List<Order> orders = orderRepository.findByUserId(user.getId());
       return DtoConverter.converToOrderResponseList(orders);
    }
}
