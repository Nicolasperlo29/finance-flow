package org.example.notificationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE =
            "fintech-exchange";

    // =========================
    // MAILS
    // =========================

    public static final String QUEUE =
            "cola-mails";

    public static final String ROUTING_KEY =
            "usuario.registrado";

    // =========================
    // TRANSFERS
    // =========================

    public static final String TRANSFER_QUEUE =
            "cola-transfers";

    public static final String TRANSFER_ROUTING_KEY =
            "transferencia.realizada";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    // =========================
    // MAIL QUEUE
    // =========================

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Binding binding(
            Queue queue,
            TopicExchange exchange
    ) {

        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    // =========================
    // TRANSFER QUEUE
    // =========================

    @Bean
    public Queue transferQueue() {
        return new Queue(TRANSFER_QUEUE);
    }

    @Bean
    public Binding transferBinding(
            Queue transferQueue,
            TopicExchange exchange
    ) {

        return BindingBuilder
                .bind(transferQueue)
                .to(exchange)
                .with(TRANSFER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}