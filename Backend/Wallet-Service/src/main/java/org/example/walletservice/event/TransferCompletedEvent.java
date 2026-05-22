package org.example.walletservice.event;

import java.math.BigDecimal;

public record TransferCompletedEvent(

        String email,

        BigDecimal amount,

        String userNameDestino
) {
}