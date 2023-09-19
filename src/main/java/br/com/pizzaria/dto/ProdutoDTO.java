package br.com.pizzaria.dto;

import br.com.pizzaria.entity.Estoque;
import br.com.pizzaria.entity.Produto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ProdutoDTO extends AbstractDTO{



    private int quantidade;


    private Estoque estoques;


    private float precoProduto;
}
