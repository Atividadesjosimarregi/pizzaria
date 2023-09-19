package br.com.pizzaria.dto;

import br.com.pizzaria.entity.Estoque;
import lombok.Data;

@Data
public class ProdutoDTO extends AbstractDTO{



    private int quantidade;


    private Estoque estoques;


    private float precoProduto;

    public ProdutoDTO(){

    }
    public ProdutoDTO(int quantidade, Estoque estoques, float precoProduto) {
        this.quantidade = quantidade;
        this.estoques = estoques;
        this.precoProduto = precoProduto;
    }
}
