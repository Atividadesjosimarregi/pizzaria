package br.com.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pizzas",schema = "public")
public class Pizza extends AbstractEntity {

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pizza_sabor",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {
                            "pizza_id",
                            "sabor_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "pizza_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "sabor_id"
            )
    )
    private List<Sabor> sabores;

    @Getter @Setter
    @Column(name = "preco")
    private float preco;


    @Getter @Setter
    @Column (name = "quantidade",nullable = false)
    private int quantidade;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "tamanho", nullable = false)
    private Tamanho tamanho;


    public Pizza(){

    }
    public Pizza(Long id,List<Sabor> sabores, float preco, int quantidade, Tamanho tamanho) {
        this.id = id;
        this.sabores = sabores;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tamanho = tamanho;
    }
}
