package org.example.notificationservice.consumer;

import lombok.RequiredArgsConstructor;
import org.example.notificationservice.config.RabbitConfig;
import org.example.notificationservice.event.TransferCompletedEvent;
import org.example.notificationservice.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletEventConsumer {

    private final EmailService service;

    @RabbitListener(
            queues = RabbitConfig.TRANSFER_QUEUE
    )
    public void consumeTransfer(
            TransferCompletedEvent event
    ) {

        service.sendTransferEmail(event.amount(), event.email(), event.userNameDestino());
    }
}
