package br.com.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "logins",schema = "public")
public class Login extends abstractEntity{

    @Getter @Setter
    @Column(name = "login",nullable = false,unique = true,length = 40)
    private String login;

    @Getter @Setter
    @Column(name = "senha",nullable = false,length = 40)
    private String senha;

}
