package org.example.walletservice.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.walletservice.client.AuthClient;
import org.example.walletservice.dto.Request.TransferRequest;
import org.example.walletservice.dto.Response.TransactionResponse;
import org.example.walletservice.dto.Response.UserResponse;
import org.example.walletservice.dto.TransactionStatus;
import org.example.walletservice.entity.Transaction;
import org.example.walletservice.entity.Wallet;
import org.example.walletservice.event.TransferCompletedEvent;
import org.example.walletservice.exception.InsufficientBalanceException;
import org.example.walletservice.exception.WalletNotFoundException;
import org.example.walletservice.producer.WalletEventProducer;
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

    private final WalletEventProducer walletEventProducer;

    private final AuthClient authClient;

    @Transactional
    @Override
    public TransactionResponse transfer(
            TransferRequest request
    ) {

        Wallet origen = walletRepository
                .findByUserId(
                        request.getUserIdOrigen()
                )
                .orElseThrow(
                        () -> new WalletNotFoundException(
                                "Wallet origen no encontrada"
                        )
                );

        Wallet destino = walletRepository
                .findByAlias(
                        request.getAliasDestino()
                )
                .orElseThrow(
                        () -> new WalletNotFoundException(
                                "Wallet destino no encontrada"
                        )
                );

        if (
                origen.getId()
                        .equals(destino.getId())
        ) {

            throw new RuntimeException(
                    "No puedes transferirte a ti mismo"
            );
        }

        if (
                origen.getBalance()
                        .compareTo(request.getMonto()) < 0
        ) {

            throw new InsufficientBalanceException(
                    "Saldo insuficiente"
            );
        }

        origen.setBalance(
                origen.getBalance()
                        .subtract(request.getMonto())
        );

        destino.setBalance(
                destino.getBalance()
                        .add(request.getMonto())
        );

        Transaction transaction =
                Transaction.builder()
                        .sourceWalletId(
                                origen.getId()
                        )
                        .destinationWalletId(
                                destino.getId()
                        )
                        .amount(request.getMonto())
                        .status(
                                TransactionStatus.COMPLETED
                        )
                        .build();

        transactionRepository.save(transaction);

        UserResponse userOrigen = getUser(origen.getUserId());
        UserResponse userDestino = getUser(destino.getUserId());

        walletEventProducer
                .sendTransferCompletedEvent(

                        new TransferCompletedEvent(
                                userOrigen.getEmail(),
                                request.getMonto(),
                                userDestino.getUserNameDestino()
                        )
                );

        walletRepository.save(origen);
        walletRepository.save(destino);

        return new TransactionResponse(
                "Transfer successful",
                request.getMonto()
        );
    }

    private UserResponse getUser(Long userId) {
        UserResponse user = authClient.getUser(userId);
        return user;
    }
}
