package org.workintech.service.user;

import org.workintech.dto.user.PaymentResponse;
import org.workintech.entity.user.Payment;

import java.util.List;

public interface PaymentService {
    PaymentResponse save(Payment payment, String token);
    List<PaymentResponse> getByUserToken(String token);
    String delete(Long id, String token);
}
