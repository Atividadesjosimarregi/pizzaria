package br.com.pizzaria.dto;


import lombok.Data;


@Data
public class LoginDTO extends AbstractDTO {


    private String username;


    private String password;

    public LoginDTO(){

    }
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
