package br.com.pizzaria.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "produtos",schema = "public")
public class Produto extends AbstractEntity {

    @Getter @Setter
    @Column(name = "quantidade",nullable = false)
    private int quantidade;

    @Getter @Setter
    @Column(name = "precoProduto",nullable = false)
    private float precoProduto;

    public Produto(){

    }
    public Produto(Long id,int quantidade, float precoProduto) {
        this.id = id;
        this.quantidade = quantidade;
        this.precoProduto = precoProduto;

    }
}
