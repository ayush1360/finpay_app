package com.finpay.auth.controller;


import com.finpay.auth.dto.response.UserProfileResponse;
import com.finpay.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user/me")
    public UserProfileResponse currentUser() {
        return userService.getCurrentUser();
    }
}
