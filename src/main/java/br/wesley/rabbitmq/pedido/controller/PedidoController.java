package br.wesley.rabbitmq.pedido.controller;

import br.wesley.rabbitmq.pedido.model.Pedido;
import br.wesley.rabbitmq.pedido.repository.PedidoRepository;
import br.wesley.rabbitmq.pedido.service.PedidoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        Pedido salvo = pedidoRepository.save(pedido);
        rabbitTemplate.convertAndSend("pagamento.pedido", salvo);
        return salvo;
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }
}
