package br.com.pizzaria.dto;


import lombok.Data;


@Data
public class FuncionarioDTO extends AbstractDTO {


    private String nome;


    public FuncionarioDTO() {

    }

    public FuncionarioDTO(String nome) {
        this.nome = nome;
    }
}



