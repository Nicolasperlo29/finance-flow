package org.example.authservice.producer;

import lombok.RequiredArgsConstructor;
import org.example.authservice.config.RabbitConfig;
import org.example.authservice.event.UserRegisteredEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendUserRegisteredEvent(
            UserRegisteredEvent event
    ) {

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                event
        );
    }
}
