package br.com.pizzaria.dto;

import br.com.pizzaria.entity.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDTO extends AbstractDTO{



    private String observacoes;


    private Cliente cliente;


    private boolean entrega;

    private BigDecimal preco;


    private Status status;


    private List<Pizza> pizzas;


    private List<Produto> produtos;



    private boolean delivery;


    private boolean cancelado;


    private boolean pagamentoCartao;

    private LocalDateTime cadastro;


    private Funcionario funcionario;





}
