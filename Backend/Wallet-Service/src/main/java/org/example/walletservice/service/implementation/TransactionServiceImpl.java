package org.example.walletservice.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.walletservice.dto.Request.TransferRequest;
import org.example.walletservice.dto.Response.TransactionResponse;
import org.example.walletservice.entity.Wallet;
import org.example.walletservice.exception.InsufficientBalanceException;
import org.example.walletservice.exception.WalletNotFoundException;
import org.example.walletservice.repository.TransactionRepository;
import org.example.walletservice.repository.WalletRepository;
import org.example.walletservice.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final WalletRepository walletRepository;

    @Transactional
    @Override
    public TransactionResponse transfer(TransferRequest transferRequest) {

        Optional<Wallet> walletOrigen = walletRepository.findByUserId(transferRequest.getUserIdOrigen());
        Optional<Wallet> walletDestino = walletRepository.findByUserId(transferRequest.getUserId());

        if (walletOrigen.isEmpty() || walletDestino.isEmpty()) {
            throw new WalletNotFoundException("No se encuentra la wallet");
        }

        BigDecimal actualOrigen = walletOrigen.get().getBalance();

        if (actualOrigen.compareTo(transferRequest.getMonto()) < 0) {
            throw new InsufficientBalanceException("No tienes suficiente saldo");
        }

        BigDecimal nuevoOrigen = actualOrigen.subtract(transferRequest.getMonto());
        walletOrigen.get().setBalance(nuevoOrigen);
        walletRepository.save(walletOrigen.get());

        BigDecimal actualDestino = walletDestino.get().getBalance().add(transferRequest.getMonto());
        walletDestino.get().setBalance(actualDestino);
        walletRepository.save(walletDestino.get());

        return new TransactionResponse("Ok", transferRequest.getMonto());
    }
}
