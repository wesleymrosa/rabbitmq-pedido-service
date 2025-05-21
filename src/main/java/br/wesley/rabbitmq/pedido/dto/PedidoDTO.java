package br.wesley.rabbitmq.pedido.dto;

public class PedidoDTO {

    private String nomeCliente;
    private Double valorTotal;

    public PedidoDTO() {
    }

    public PedidoDTO(String nomeCliente, Double valorTotal) {
        this.nomeCliente = nomeCliente;
        this.valorTotal = valorTotal;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
