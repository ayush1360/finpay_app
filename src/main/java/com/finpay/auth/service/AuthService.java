package com.finpay.auth.service;

import com.finpay.auth.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);
}