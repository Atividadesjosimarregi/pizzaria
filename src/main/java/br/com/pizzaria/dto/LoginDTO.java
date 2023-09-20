package br.com.pizzaria.dto;


import lombok.Data;


@Data
public class LoginDTO extends AbstractDTO {


    private String loginn;


    private String senha;

    public LoginDTO(){

    }
    public LoginDTO(String loginn, String senha) {
        this.loginn = loginn;
        this.senha = senha;
    }
}
