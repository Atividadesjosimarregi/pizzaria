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
    @Column(name = "numero",nullable = false)
    private int numero;

    @Getter @Setter
    @Column(name = "observacao", length = 100)
    private String observacao;

    @Getter @Setter
    @Column(name = "cep",nullable = false,length = 15)
    private String cep;

    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Endereco(){

    }

    public Endereco(Long id,String rua, String bairro, int numero, String observacao, String cep,Cliente cliente) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.observacao = observacao;
        this.cep = cep;
        this.cliente = cliente;
    }
}
