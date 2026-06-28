package com.finpay.auth.controller;

import com.finpay.auth.common.ApiResponse;
import com.finpay.auth.dto.request.DepositRequest;
import com.finpay.auth.dto.response.WalletResponse;
import com.finpay.auth.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/me")
    public ApiResponse<WalletResponse> getMyWallet(){

        return ApiResponse.success(
                walletService.getMyWallet(),
                "Wallet fetched successfully"
        );
    }

    @PostMapping("/deposit")
    public ApiResponse<WalletResponse> deposit(
            @Valid @RequestBody DepositRequest request) {

        return ApiResponse.success(
                walletService.deposit(request),
                "Amount deposited successfully"
        );
    }

}