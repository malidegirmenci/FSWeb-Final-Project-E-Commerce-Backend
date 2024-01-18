package org.workintech.dto.user;

public record PaymentResponse(String number,String name, String expiryMonth, String expiryYear,String cvc) {
}
