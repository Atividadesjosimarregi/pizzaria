package br.com.pizzaria.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FuncionarioDTO extends AbstractDTO {


    private String nome;


    public FuncionarioDTO() {

    }

    public FuncionarioDTO(String nome) {
        this.nome = nome;
    }
}



