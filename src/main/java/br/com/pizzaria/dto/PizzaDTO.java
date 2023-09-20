package br.com.pizzaria.dto;


import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.entity.Tamanho;
import lombok.Data;

import java.util.List;

@Data
public class PizzaDTO extends AbstractDTO{


    private List<Sabor> sabores;

    private float preco;

    private int quantidade;


    private Tamanho tamanho;

    public PizzaDTO(){

    }
    public PizzaDTO(List<Sabor> sabores, float preco, int quantidade, Tamanho tamanho) {
        this.sabores = sabores;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tamanho = tamanho;
    }
}
