package br.com.pizzaria.dto;

import br.com.pizzaria.entity.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDTO extends AbstractDTO{



    private String observacoes;


    private Cliente cliente;

    private float preco;

    private Status status;

    private List<Pizza> pizzas;


    private List<Produto> produtos;

    private boolean entrega;
    private boolean delivery;


    private boolean cancelado;


    private boolean pagamentoCartao;

    private LocalDateTime cadastro;


    private Funcionario funcionario;


    public PedidoDTO(){

    }
    public PedidoDTO(String observacoes, Cliente cliente, float preco, Status status, List<Pizza> pizzas, List<Produto> produtos, boolean entrega, boolean delivery, boolean cancelado, boolean pagamentoCartao, LocalDateTime cadastro, Funcionario funcionario) {
        this.observacoes = observacoes;
        this.cliente = cliente;
        this.preco = preco;
        this.status = status;
        this.pizzas = pizzas;
        this.produtos = produtos;
        this.entrega = entrega;
        this.delivery = delivery;
        this.cancelado = cancelado;
        this.pagamentoCartao = pagamentoCartao;
        this.cadastro = cadastro;
        this.funcionario = funcionario;
    }
}
