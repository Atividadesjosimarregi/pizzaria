package br.com.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Estoque_tb",schema = "public")
public class Estoque extends AbstractEntity {

    @Getter @Setter
    @Column(name = "preco",nullable = false)
    private float preco;

    @Getter @Setter
    @Column(name = "nome",nullable = false,unique = true,length = 150)
    private String nome;

    public Estoque(){

    }
    public Estoque(Long id,float preco, String nome) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
    }
}
