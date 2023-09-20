package br.com.pizzaria.dto;

import lombok.Data;


@Data
public class EstoqueDTO extends AbstractDTO {


    private float preco;

    private String nome;


    public EstoqueDTO(){

    }
    public EstoqueDTO(float preco, String nome) {
        this.preco = preco;
        this.nome = nome;
    }
}
