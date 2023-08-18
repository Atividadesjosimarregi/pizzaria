package br.com.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "pedidos",schema = "public")
public class Pedido extends abstractEntity{

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
    @JoinColumn(name = "produtos")
    private List<Produto> produtos;



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



    @PrePersist
    private void prePersist(){
        this.cadastro = LocalDateTime.now();
    }

}
