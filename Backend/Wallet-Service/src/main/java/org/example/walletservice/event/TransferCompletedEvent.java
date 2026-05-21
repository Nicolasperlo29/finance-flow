package org.example.walletservice.event;

import java.math.BigDecimal;

public record TransferCompletedEvent(

        Long sourceWalletId,

        Long destinationWalletId,

        BigDecimal amount
) {
}