package com.multigenesys.ecommerce.controller;

import com.multigenesys.ecommerce.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam Long orderId) throws Exception {

        return paymentService.createCheckoutSession(orderId);
    }

    @GetMapping("/success")
    public String success(){
        return "Payment Successful";
    }

    @GetMapping("/cancel")
    public String cancel(){
        return "Payment Cancelled";
    }
}