package org.example.notificationservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.notificationservice.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
}
