package org.workintech.controller.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.workintech.dto.user.PaymentResponse;
import org.workintech.entity.user.Payment;
import org.workintech.service.user.PaymentService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;
    @PostMapping("/{token}")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse save(@NotNull @RequestBody Payment payment, @NotBlank @PathVariable String token){
        return paymentService.save(payment,token);
    }

    @GetMapping("/{token}")
    public List<PaymentResponse> getAll(@NotBlank @PathVariable String token){
        return paymentService.getByUserToken(token);
    }

    @DeleteMapping("/{token}/{id}")
    public String delete(@NotBlank @PathVariable String token,@NotNull @PathVariable Long id){
        return paymentService.delete(id,token);
    }
}
