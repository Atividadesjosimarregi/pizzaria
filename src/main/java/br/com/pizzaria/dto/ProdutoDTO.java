package br.com.pizzaria.dto;

import br.com.pizzaria.entity.Estoque;
import lombok.Data;

@Data
public class ProdutoDTO extends AbstractDTO{



    private int quantidade;


    private float precoProduto;

    public ProdutoDTO(){

    }
    public ProdutoDTO(int quantidade, float precoProduto) {
        this.quantidade = quantidade;
        this.precoProduto = precoProduto;
    }
}
