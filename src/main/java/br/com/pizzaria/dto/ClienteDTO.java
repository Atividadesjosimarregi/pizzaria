package br.com.pizzaria.dto;

import br.com.pizzaria.entity.Endereco;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO extends AbstractDTO{

    private String nome;

    private List<Endereco> enderecos;


    public ClienteDTO() {

    }
    public ClienteDTO(String nome) {
        this.nome = nome;
    }
}
