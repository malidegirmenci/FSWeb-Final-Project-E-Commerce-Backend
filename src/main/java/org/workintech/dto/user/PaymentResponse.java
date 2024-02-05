package org.workintech.dto.user;

public record PaymentResponse(Long id, String title, String number,String name, String expiryMonth, String expiryYear,String cvc) {
}
