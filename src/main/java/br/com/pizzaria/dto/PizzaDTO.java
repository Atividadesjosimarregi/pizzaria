package br.com.pizzaria.dto;


import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.entity.Tamanho;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PizzaDTO extends AbstractDTO{


    private List<Sabor> sabores;

    private BigDecimal preco;

    private int quantidade;


    private Tamanho tamanho;




}
