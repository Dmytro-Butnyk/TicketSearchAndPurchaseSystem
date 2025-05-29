package com.example.ticketsearchandpurchasesystem.application.services;

import com.example.ticketsearchandpurchasesystem.domain.entities.Customer;
import com.example.ticketsearchandpurchasesystem.infrastructure.security.JwtUtil;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.CustomerRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    public AuthService(CustomerRepository customerRepository, JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
    }

    @Async
    public CompletableFuture<String> login(String email) {
        try {
            Customer customer = customerRepository.findByEmail(email);
            if (customer == null) {
                throw new RuntimeException("Customer not found: " + email);
            }
            String token = jwtUtil.generateToken(email);
            return CompletableFuture.completedFuture(token);
        } catch (Exception ex) {
            return CompletableFuture.failedFuture(ex);
        }
    }
}
