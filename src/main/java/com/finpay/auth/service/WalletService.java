package com.finpay.auth.service;

import com.finpay.auth.dto.request.DepositRequest;
import com.finpay.auth.dto.response.WalletResponse;

public interface WalletService {

    WalletResponse getMyWallet();

    WalletResponse deposit(DepositRequest request);
}