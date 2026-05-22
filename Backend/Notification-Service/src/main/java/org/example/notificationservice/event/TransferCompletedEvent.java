package org.example.notificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record TransferCompletedEvent(
        String email,
        BigDecimal amount,
        String userNameDestino
) {
}