package br.wesley.rabbitmq.pedido.service;

import br.wesley.rabbitmq.pedido.dto.PedidoDTO;
import br.wesley.rabbitmq.pedido.model.Pedido;
import br.wesley.rabbitmq.pedido.repository.PedidoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Pedido criar(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setNomeCliente(dto.getNomeCliente());
        pedido.setValorTotal(dto.getValorTotal());

        Pedido salvo = pedidoRepository.save(pedido);
        rabbitTemplate.convertAndSend("pagamento.pedido", salvo);
        return salvo;
    }
}
