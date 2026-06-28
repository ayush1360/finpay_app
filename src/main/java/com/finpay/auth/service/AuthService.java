package com.finpay.auth.service;

import com.finpay.auth.dto.request.LoginRequest;
import com.finpay.auth.dto.request.RegisterRequest;
import com.finpay.auth.dto.response.LoginResponse;
import com.finpay.auth.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}