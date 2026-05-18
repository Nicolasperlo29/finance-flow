package org.example.notificationservice.consumer;

import org.example.notificationservice.config.RabbitConfig;
import org.example.notificationservice.event.UserRegisteredEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AuthEventConsumer {

    @RabbitListener(
            queues = RabbitConfig.QUEUE
    )
    public void consume(
            UserRegisteredEvent event
    ) {

        System.out.println(
                "User registered: "
                        + event.email()
        );
    }
}
