package com.example.ticketsearchandpurchasesystem.api;

import com.example.ticketsearchandpurchasesystem.application.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public CompletableFuture<ResponseEntity<String>> login(String email) {
        return authService.login(email)
                .thenApply(token -> ResponseEntity.ok(token))
                .exceptionally(ex -> ResponseEntity.status(500).body("Login failed: " + ex.getMessage()));
    }
}
