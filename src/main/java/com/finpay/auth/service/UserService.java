package com.finpay.auth.service;

import com.finpay.auth.dto.response.UserProfileResponse;

public interface UserService {

    UserProfileResponse getCurrentUser();
}