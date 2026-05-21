package org.example.walletservice.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

    private Long userIdOrigen;

    private String aliasDestino;

    private BigDecimal monto;
}
