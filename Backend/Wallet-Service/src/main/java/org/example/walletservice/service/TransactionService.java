package org.example.walletservice.service;

import org.example.walletservice.dto.Request.TransferRequest;
import org.example.walletservice.dto.Response.TransactionResponse;

public interface TransactionService {

    TransactionResponse transfer(TransferRequest transferRequest);
}
