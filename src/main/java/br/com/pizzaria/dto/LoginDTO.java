package br.com.pizzaria.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Data
public class LoginDTO extends AbstractDTO {


    private String login;


    private String senha;

    public LoginDTO(){

    }
    public LoginDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
