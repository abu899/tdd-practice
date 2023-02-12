package tdd.practice.productorderservice.payment.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import tdd.practice.productorderservice.payment.domain.Payment;

interface PaymentRepository extends JpaRepository<Payment, Long> {
}
