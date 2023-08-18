package br.com.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "endereços",schema = "public")
public class Endereco extends abstractEntity{

    @Getter @Setter
    @Column(name = "rua",nullable = false, length = 50)
    private String rua;

    @Getter @Setter
    @Column(name = "bairro",nullable = false, length = 50)
    private String bairro;

    @Getter @Setter
    @Column(name = "numero",nullable = false, length = 50)
    private int numero;

    @Getter @Setter
    @Column(name = "observacao", length = 100)
    private String observacao;

    @Getter @Setter
    @Column(name = "cep",nullable = false)
    private String cep;

    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;



}
