package org.example.walletservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.walletservice.dto.Request.TransferRequest;
import org.example.walletservice.dto.Response.TransactionResponse;
import org.example.walletservice.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(@RequestBody TransferRequest transferRequest) {
        return ResponseEntity.ok(transactionService.transfer(transferRequest));
    }
}
