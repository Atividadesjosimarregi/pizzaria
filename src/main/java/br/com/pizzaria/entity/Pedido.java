package br.com.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "pedidos",schema = "public")
public class Pedido extends AbstractEntity {

    @Getter @Setter
    @Column(name = "observacoes",length = 80)
    private String observacoes;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id",nullable = false)
    private Cliente cliente;

    @Getter @Setter
    @Column(name = "preco")
    private float preco;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "tipo", nullable = false)
    private Status status;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id")
    private List<Pizza> pizzas;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "produtos_idd")
    private List<Produto> produtos;

    @Getter @Setter
    @Column(name = "entrega")
    private boolean entrega;

    @Getter @Setter
    @Column(name = "delivery")
    private boolean delivery;

    @Getter @Setter
    @Column(name = "cancelado")
    private boolean cancelado;

    @Getter @Setter
    @Column(name = "pagamentoCartao")
    private boolean pagamentoCartao;

    @Getter @Setter
    @Column(name = "dtCadastro")
    private LocalDateTime cadastro;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario")
    private Funcionario funcionario;


    public Pedido(){

    }
    public Pedido(Long id,String observacoes, Cliente cliente, float preco, Status status, List<Pizza> pizzas, List<Produto> produtos, boolean entrega, boolean delivery, boolean cancelado, boolean pagamentoCartao, LocalDateTime cadastro, Funcionario funcionario) {
        this.id = id;
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

    @PrePersist
    private void prePersist(){
        this.cadastro = LocalDateTime.now();
    }

}
