package br.com.pizzaria.dto;


import br.com.pizzaria.entity.Cliente;
import lombok.Data;

@Data
public class EnderecoDTO extends AbstractDTO {


    private String rua;


    private String bairro;


    private int numero;


    private String observacao;


    private String cep;


    private Cliente cliente;

    public EnderecoDTO(){

    }
    public EnderecoDTO(String rua, String bairro, int numero, String observacao, String cep,Cliente cliente) {
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.observacao = observacao;
        this.cep = cep;
        this.cliente = cliente;
    }
}
