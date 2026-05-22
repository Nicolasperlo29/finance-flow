package org.example.notificationservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.notificationservice.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(String to) {
        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(to);

        message.setSubject(
                "Welcome to FinanceFlow"
        );

        message.setText(
                "Your account was created successfully."
        );

        mailSender.send(message);
    }

    @Override
    public void sendTransferEmail(BigDecimal amount, String to, String userNameDestino) {
        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(to);

        message.setSubject(
                "Transferencia realizada"
        );

        message.setText(
                "Tu transferencia de " + amount + " ya fue ralizada. Hacia " + userNameDestino + "."
        );

        mailSender.send(message);
    }
}
