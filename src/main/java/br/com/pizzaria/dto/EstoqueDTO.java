package br.com.pizzaria.dto;

import br.com.pizzaria.entity.Endereco;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EstoqueDTO extends AbstractDTO {


    private float preco;

    private String nome;
}