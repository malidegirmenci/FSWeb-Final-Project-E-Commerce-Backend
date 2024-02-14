package org.workintech.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.user.PaymentResponse;
import org.workintech.entity.user.Payment;
import org.workintech.entity.user.User;
import org.workintech.exceptions.EcommerceException;
import org.workintech.repository.user.PaymentRepository;
import org.workintech.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService{
    private PaymentRepository paymentRepository;
    private UserRepository userRepository;
    @Override
    public PaymentResponse save(Payment payment, String token) {
        Optional<User> optionalUser = userRepository.findUserByToken(token);
        if(optionalUser.isPresent()){
            optionalUser.get().getPayments().add(payment);
            return DtoConverter.convertToPaymentResponse(paymentRepository.save(payment));
        }
        throw new EcommerceException("The given token is wrong. So the payment could not saved", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public List<PaymentResponse> getByUserToken(String token) {
        Optional<User> user = userRepository.findUserByToken(token);
        return DtoConverter.convertToPaymentResponseList(user.orElseThrow().getPayments().stream().toList());
    }

    @Override
    public String delete(Long id, String token) {
        User user = userRepository.findUserByToken(token).orElseThrow(()->new EcommerceException("The user with given id could not find!",HttpStatus.UNAUTHORIZED));
        Payment payment = paymentRepository.findById(id).orElseThrow(()->new EcommerceException("The card with given id could not find!",HttpStatus.NOT_FOUND));
        user.getPayments().remove(payment);
        paymentRepository.deleteById(id);
        return "The Card deleted";
    }
}
