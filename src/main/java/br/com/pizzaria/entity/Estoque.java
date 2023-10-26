package br.com.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Estoque",schema = "public")
public class Estoque extends AbstractEntity {

    @Getter @Setter
    @Column(name = "preco")
    private float preco;

    @Getter @Setter
    @Column(name = "nome")
    private String nome;

    @Getter @Setter
    @Column(name = "quantidade")
    private int quantidade;

    @Getter @Setter
    @Column (name = "total_produto")
    private float totalProduto;

    public Estoque(){

    }
    public Estoque(Long id,float preco, String nome, int quantidade, float totalProduto) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.quantidade = quantidade;
        this.totalProduto = totalProduto;
    }
}
