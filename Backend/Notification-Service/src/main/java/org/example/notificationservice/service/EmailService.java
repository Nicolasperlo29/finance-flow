package org.example.notificationservice.service;

import java.math.BigDecimal;

public interface EmailService {

    void sendVerificationEmail(String to);

    void sendTransferEmail(BigDecimal amount, String to, String userNameDestino);
}
