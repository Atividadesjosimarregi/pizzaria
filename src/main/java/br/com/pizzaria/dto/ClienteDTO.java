package br.com.pizzaria.dto;

import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.entity.Login;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO extends AbstractDTO{

    private String nome;

    private List<Endereco> enderecos;


}