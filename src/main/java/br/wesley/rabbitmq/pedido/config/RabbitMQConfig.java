package br.wesley.rabbitmq.pedido.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String NOME_FILA = "pagamento.pedido";
    public static final String NOME_EXCHANGE = "pagamento.direct";
    public static final String ROUTING_KEY = "pagamento.pedido";

    @Bean
    public Queue filaPedido() {
        return new Queue(NOME_FILA, true);
    }

    @Bean
    public DirectExchange exchangePedido() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    @Bean
    public Binding bindingPedido(Queue filaPedido, DirectExchange exchangePedido) {
        return BindingBuilder
                .bind(filaPedido)
                .to(exchangePedido)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter jsonMessageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter);
        return template;
    }
}
