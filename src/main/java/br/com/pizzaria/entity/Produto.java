package br.com.pizzaria.entity;

import com.zaxxer.hikari.util.ClockSource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Entity
@Table(name = "produtos",schema = "public")
public class Produto extends abstractEntity{

    @Getter @Setter
    @Column(name = "quantidade",nullable = false)
    private int quantidade;

    @Getter @Setter
    @Column(name = "precoProduto",nullable = false)
    private float precoProduto;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estoque_id")
    private Estoque estoques;


    public Produto(){

    }
    public Produto(Long id,int quantidade,Estoque estoques, float precoProduto) {
        this.id = id;
        this.quantidade = quantidade;
        this.estoques = estoques;
        this.precoProduto = precoProduto;

    }
}
