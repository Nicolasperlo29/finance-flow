package org.example.walletservice.service;

import org.example.walletservice.dto.Response.BalanceResponse;
import org.example.walletservice.dto.Response.WalletResponse;

public interface WalletService {

    WalletResponse createWallet(Long userId);

    BalanceResponse getBalance(Long userId);
}
