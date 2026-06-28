package com.finpay.auth.controller;

import com.finpay.auth.common.ApiResponse;
import com.finpay.auth.dto.request.LoginRequest;
import com.finpay.auth.dto.request.RegisterRequest;
import com.finpay.auth.dto.response.LoginResponse;
import com.finpay.auth.dto.response.RegisterResponse;
import com.finpay.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request){
        RegisterResponse response = authService.register(request);
        ApiResponse<RegisterResponse> apiResponse =
                ApiResponse.<RegisterResponse>builder()
                        .success(true)
                        .message("User registered successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        ApiResponse<LoginResponse> apiResponse =
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Login Successful")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}
