package org.example.walletservice.producer;

import lombok.RequiredArgsConstructor;
import org.example.walletservice.config.RabbitConfig;
import org.example.walletservice.event.TransferCompletedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendTransferCompletedEvent(
            TransferCompletedEvent event
    ) {

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.TRANSFER_COMPLETED_KEY,
                event
        );
    }
}