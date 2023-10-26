package br.com.pizzaria.dto;

import lombok.Data;


@Data
public class EstoqueDTO extends AbstractDTO {


    private float preco;

    private String nome;

    private int quantidade;


    private float totalProduto;


    public EstoqueDTO(){

    }
    public EstoqueDTO(float preco, String nome, int quantidade, float totalProduto) {
        this.preco = preco;
        this.nome = nome;
        this.quantidade = quantidade;
        this.totalProduto = totalProduto;
    }
}
