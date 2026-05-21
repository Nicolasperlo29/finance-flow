package org.example.notificationservice.consumer;

import lombok.RequiredArgsConstructor;
import org.example.notificationservice.config.RabbitConfig;
import org.example.notificationservice.event.TransferCompletedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    @RabbitListener(
            queues = RabbitConfig.TRANSFER_QUEUE
    )
    public void consumeTransfer(
            TransferCompletedEvent event
    ) {

        System.out.println(
                "Transfer completed: "
                        + event.getAmount()
        );
    }
}
