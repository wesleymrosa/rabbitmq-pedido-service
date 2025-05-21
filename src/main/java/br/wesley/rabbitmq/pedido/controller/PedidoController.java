package br.wesley.rabbitmq.pedido.controller;

import br.wesley.rabbitmq.pedido.model.Pedido;
import br.wesley.rabbitmq.pedido.repository.PedidoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        Pedido salvo = pedidoRepository.save(pedido);
        rabbitTemplate.convertAndSend("pagamento.pedido", salvo);
        return salvo;
    }
}