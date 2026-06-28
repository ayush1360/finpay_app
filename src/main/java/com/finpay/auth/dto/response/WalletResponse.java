package com.finpay.auth.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WalletResponse {

    private String walletNumber;

    private BigDecimal balance;

    private String currency;

    private String status;
}