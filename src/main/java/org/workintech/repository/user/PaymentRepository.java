package org.workintech.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workintech.entity.user.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
