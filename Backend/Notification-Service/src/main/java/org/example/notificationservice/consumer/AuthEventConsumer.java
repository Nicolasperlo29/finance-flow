package org.example.notificationservice.consumer;

import lombok.RequiredArgsConstructor;
import org.example.notificationservice.config.RabbitConfig;
import org.example.notificationservice.event.UserRegisteredEvent;
import org.example.notificationservice.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthEventConsumer {

    private final EmailService service;

    @RabbitListener(
            queues = RabbitConfig.QUEUE
    )
    public void consume(
            UserRegisteredEvent event
    ) {

        service.sendVerificationEmail(event.email());
        
        System.out.println(
                "User registered: "
                        + event.email()
        );
    }
}
