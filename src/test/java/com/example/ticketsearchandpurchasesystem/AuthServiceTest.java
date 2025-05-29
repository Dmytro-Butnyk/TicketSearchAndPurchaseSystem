package com.example.ticketsearchandpurchasesystem;

import com.example.ticketsearchandpurchasesystem.application.services.AuthService;
import com.example.ticketsearchandpurchasesystem.domain.entities.Customer;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.CustomerRepository;
import com.example.ticketsearchandpurchasesystem.infrastructure.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @Test
    void login_shouldReturnToken_whenCustomerExists() throws Exception {
        String email = "test@example.com";
        Customer customer = new Customer();
        Mockito.when(customerRepository.findByEmail(email)).thenReturn(customer);
        Mockito.when(jwtUtil.generateToken(email)).thenReturn("mock-token");

        CompletableFuture<String> future = authService.login(email);

        assertEquals("mock-token", future.get());
    }

    @Test
    void login_shouldThrowException_whenCustomerNotFound() {
        String email = "notfound@example.com";
        Mockito.when(customerRepository.findByEmail(email)).thenReturn(null);

        CompletableFuture<String> future = authService.login(email);

        assertThrows(ExecutionException.class, future::get);
    }
}

