package com.finpay.auth.service;

import com.finpay.auth.dto.request.LoginRequest;
import com.finpay.auth.dto.request.RegisterRequest;
import com.finpay.auth.dto.response.LoginResponse;
import com.finpay.auth.dto.response.RegisterResponse;
import com.finpay.auth.entity.User;
import com.finpay.auth.entity.Wallet;
import com.finpay.auth.enums.Role;
import com.finpay.auth.enums.WalletStatus;
import com.finpay.auth.exception.EmailAlreadyExistsException;
import com.finpay.auth.repository.UserRepository;
import com.finpay.auth.repository.WalletRepository;
import com.finpay.auth.security.CustomUserDetailsService;
import com.finpay.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final WalletRepository walletRepository;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email Already exits");
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();
       User savedUser =  userRepository.save(user);
        Wallet wallet = Wallet.builder()
                .walletNumber(generateWalletNumber(savedUser.getId()))
                .balance(BigDecimal.ZERO)
                .currency("INR")
                .status(WalletStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .user(savedUser)
                .build();

        walletRepository.save(wallet);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .fullName((savedUser.getFullName()))
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .build();
    }

    private String generateWalletNumber(Long userId) {
        return "FP" + String.format("%08d", userId);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserDetails userDetails  =
                customUserDetailsService.loadUserByUsername(
                        request.getEmail()
                );
        String token = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(3600000L)
                .build();
    }


}
