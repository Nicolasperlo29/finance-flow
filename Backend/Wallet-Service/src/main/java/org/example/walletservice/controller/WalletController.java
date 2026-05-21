package org.example.walletservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.walletservice.dto.Request.WalletRequest;
import org.example.walletservice.dto.Response.BalanceResponse;
import org.example.walletservice.dto.Response.WalletResponse;
import org.example.walletservice.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<WalletResponse> createWallet(@RequestBody WalletRequest request) {
        return ResponseEntity.ok(walletService.createWallet(request.getUserId()));
    }

    @GetMapping("/balance")
    public ResponseEntity<BalanceResponse> getBalance(@RequestParam Long userId) {
        return ResponseEntity.ok(walletService.getBalance(userId));
    }
}
