package com.finpay.auth.controller;

import com.finpay.auth.common.ApiResponse;
import com.finpay.auth.dto.response.WalletResponse;
import com.finpay.auth.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}